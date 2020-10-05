#!groovy

@Library(["gcp-workflowlib@master"]) _

agentName = "gcp-agent-${new Date().getTime()}"
agentLabel = "${println 'The agent name is: ' + agentName; return agentName}"

pipeline {
    agent {
        kubernetes {
            label agentLabel
            defaultContainer 'fga-cli'
            yaml """
apiVersion: v1
kind: Pod
metadata:
  labels:
    name: ${agentLabel}
spec:
  securityContext:
    runAsUser: 1000
  containers:
    - name: fga-cli
      image: globaldevtools.bbva.com:5000/gcp/arch/bbva-fga-cli-ng:latest
      command:
        - cat
      tty: true
      resources:
        requests:
          cpu: 2
          memory: 2048Mi
        limits:
          cpu: 2
          memory: 2048Mi
  imagePullSecrets:
    - name: registrypullsecret
"""
        }
    }

    options {
        ansiColor('xterm')
        timeout(time: 60, unit: 'MINUTES')
    }

    environment {
        UUAA = ''
        SAMUEL_PROJECT_NAME = 'Pipeline Java Back GAE'
        CLI_MODE = 'jenkins'
        BOT_GCP_READER_USER = credentials('bot_gcp_reader_user')
        BOT_GCP_READER_PASSWORD = credentials('bot_gcp_reader_password')
    }

    stages {
        stage ('Samuel setup') {
            steps {
                script {
                    gcpsamuel.prepare()
                }
            }
        }

        stage ('Test & Coverage') {
            steps {
                library 'sonar@lts'
                script {
                    def statusCode = null

                    sonar([
                            waitForQualityGate: true
                    ]) {
                        withCredentials([file(credentialsId: 'gcp-maven-settings', variable: 'GCP_MAVEN_SETTINGS')]) {
                            statusCode = sh returnStatus: true, script: """
                                mvn -B -V -U -s $GCP_MAVEN_SETTINGS clean test sonar:sonar
                            """
                        }
                    }

                    if (statusCode != 0) {
                        error 'Error executing test and coverage analysis'
                    }
                }
            }
        }

        stage ('Wait for Cloud Build') {
            steps {
                script {
                    try {
                        retry (3) {
                            gcphelper.waitForCloudBuildExecution()
                        }
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        error "Error: ${e.getMessage()}"
                    }
                }
            }
        }

        stage ('Tag code') {
            steps {
                script {
                    gcphelper.createTag()
                }
            }
        }
    }

    post {
        always {
            echo "We have been through the entire pipeline"
        }
        changed {
            echo "There have been some changes from the last build"
        }
        success {
            echo "Build successful"
        }
        failure {
            echo "There have been some errors"
        }
        unstable {
            echo "Unstable"
        }
        aborted {
            echo "Aborted"
            script {
                gcphelper.delete_notifications()
            }
        }
    }
}