pipeline{
  agent any
  stages {
    stage('Build'){
      steps{
        echo 'Building..'
        sh './gradlew build'
        archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
      }
    }
    stage('Test'){
      steps{
        echo 'Testing..'
      }
    }
    stage('Deploy'){
      steps{
        echo 'Deploying..'
      }
    }
  }
}
