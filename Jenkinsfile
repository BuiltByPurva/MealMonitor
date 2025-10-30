pipeline {
    agent any

    environment {
        DOCKERHUB_REPO = 'purvakamerkarjj5499079'
        SONARQUBE_ENV = 'sonarqube'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'master', url: 'https://github.com/BuiltByPurva/MealMonitor.git'
            }
        }

        stage('SonarQube Code Quality Check') {
            steps {
                script {
                    withSonarQubeEnv('sonarqube') {
                        sh '''
                        # Run SonarQube analysis for each service
                        for service in mealmonitor-user-service mealmonitor-canteen-service mealmonitor-review-service mealmonitor-notification-service mealmonitor-poll-service mealmonitor-gateway mealmonitor-eureka-server mealmonitor-frontend; do
                            echo "Running SonarQube for $service..."
                            cd $service
                            sonar-scanner \
                                -Dsonar.projectKey=$service \
                                -Dsonar.sources=. \
                                -Dsonar.host.url=$SONAR_HOST_URL \
                                -Dsonar.login=$SONAR_AUTH_TOKEN
                            cd ..
                        done
                        '''
                    }
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    def services = [
                        'mealmonitor-user-service',
                        'mealmonitor-canteen-service',
                        'mealmonitor-review-service',
                        'mealmonitor-notification-service',
                        'mealmonitor-poll-service',
                        'mealmonitor-gateway',
                        'mealmonitor-eureka-server',
                        'mealmonitor-frontend'
                    ]

                    services.each { svc ->
                        sh "docker build -t ${DOCKERHUB_REPO}/${svc}:latest ${svc}/"
                    }
                }
            }
        }

        stage('Push Images to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub') {
                        def services = [
                            'mealmonitor-user-service',
                            'mealmonitor-canteen-service',
                            'mealmonitor-review-service',
                            'mealmonitor-notification-service',
                            'mealmonitor-poll-service',
                            'mealmonitor-gateway',
                            'mealmonitor-eureka-server',
                            'mealmonitor-frontend'
                        ]
                        services.each { svc ->
                            sh "docker push ${DOCKERHUB_REPO}/${svc}:latest"
                        }
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG_FILE')]) {
                        sh '''
                        export KUBECONFIG=$KUBECONFIG_FILE
                        kubectl apply -f k8s/
                        '''
                    }
                }
            }
        }
    }

    post {
        success {
            echo '✅ Deployment successful to Kubernetes!'
        }
        failure {
            echo '❌ Build failed! Check logs.'
        }
    }
}
