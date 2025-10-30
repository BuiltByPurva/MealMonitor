pipeline {
    agent any

    environment {
        DOCKERHUB_REPO = 'purvakamerkarjj5499079'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'master', url: 'https://github.com/BuiltByPurva/MealMonitor.git'
            }
        }

        stage('Verify Workspace') {
            steps {
                script {
                    bat 'cd'
                    bat 'dir /s'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    def services = [
                        'MealMonitor-UserService',
                        'MealMonitor-CanteenService',
                        'MealMonitor-ReviewService',
                        'MealMonitor-NotificationService',
                        'MealMonitor-PollService',
                        'MealMonitor-Gateway',
                        'MealMonitor-EurekaServer',
                        'mealmonitor-frontend'
                    ]

                    services.each { svc ->
                        echo "🚀 Building Docker image for ${svc}"
                        bat "docker build -t ${DOCKERHUB_REPO}/${svc.toLowerCase()}:latest ${svc}/"
                    }
                }
            }
        }

        stage('Push Images to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub') {
                        def services = [
                            'MealMonitor-User-Service',
                            'MealMonitor-Canteen-Service',
                            'MealMonitor-Review-Service',
                            'MealMonitor-Notification-Service',
                            'MealMonitor-Poll-Service',
                            'MealMonitor-Gateway',
                            'MealMonitor-Eureka-Server',
                            'mealmonitor-frontend'
                        ]
                        services.each { svc ->
                            bat "docker push ${DOCKERHUB_REPO}/${svc.toLowerCase()}:latest"
                        }
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG_FILE')]) {
                        bat '''
                        set KUBECONFIG=%KUBECONFIG_FILE%
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
