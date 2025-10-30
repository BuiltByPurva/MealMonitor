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
                        echo "🚀 Building Docker image for ${svc}"
                        sh "docker build -t ${DOCKERHUB_REPO}/${svc}:latest ${svc}/"
                    }
                }
            }
        }

        stage('Push Images to Docker Hub') {
            steps {
                script {
                // Jenkins Docker Hub credentials ID = 'dockerhub'
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
                            echo "📤 Pushing image: ${DOCKERHUB_REPO}/${svc}:latest"
                            sh "docker push ${DOCKERHUB_REPO}/${svc}:latest"
                        }
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    // Jenkins credentials ID = 'kubeconfig'
                    withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG_FILE')]) {
                        echo "📦 Deploying all services to Kubernetes..."
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
