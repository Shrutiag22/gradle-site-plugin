pipeline{
  agent any
  stages {
    stage('Build'){
      steps{
        echo 'Building..'
        echo $PWD
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
