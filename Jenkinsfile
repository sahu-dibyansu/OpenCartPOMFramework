pipeline {

    agent any

    tools {
        maven 'maven'
    }

    options {
        timestamps()
    }

    stages {

        stage('Build') {
            steps {

                deleteDir()

                git branch: 'master',
                    url: 'https://github.com/jglick/simple-maven-project-with-tests.git'

                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {

                always {

                    junit '**/target/surefire-reports/TEST-*.xml'

                    archiveArtifacts artifacts: 'target/*.jar',
                                      fingerprint: true
                }
            }
        }

        stage('Deploy to QA') {
            steps {
                echo 'Application Deployed to QA Environment'
            }
        }

        stage('Regression Automation Tests') {
            steps {

                deleteDir()

                git branch: 'main',
                    url: 'https://github.com/sahu-dibyansu/OpenCartPOMFramework.git'

                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {

                    bat "mvn test -DsuiteXmlFile=src/test/resources/testrunners/testng_regression.xml"

                }
            }
        }

        stage('Publish Allure Report') {
            steps {

                allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'allure-results']]
                ])

            }
        }

        stage('Publish Regression ChainTest Report') {
            steps {

                publishHTML([
                        allowMissing: true,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'target/chaintest',
                        reportFiles: 'Index.html',
                        reportName: 'Regression ChainTest Report'
                ])

            }
        }

        stage('Deploy to Stage') {
            steps {
                echo 'Application Deployed to Stage Environment'
            }
        }

        stage('Sanity Automation Tests') {
            steps {

                deleteDir()

                git branch: 'main',
                    url: 'https://github.com/sahu-dibyansu/OpenCartPOMFramework.git'

                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {

                    bat "mvn test -DsuiteXmlFile=src/test/resources/testrunners/testng_sanity.xml"

                }
            }
        }

        stage('Publish Sanity ChainTest Report') {
            steps {

                publishHTML([
                        allowMissing: true,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'target/chaintest',
                        reportFiles: 'Index.html',
                        reportName: 'Sanity ChainTest Report'
                ])

            }
        }

        stage('Deploy to PROD') {
            steps {
                echo 'Application Deployed to Production Environment'
            }
        }
    }

    post {

        always {
            cleanWs()
        }

        success {
            echo 'Pipeline executed successfully.'
        }

        failure {
            echo 'Pipeline execution failed.'
        }
    }
}