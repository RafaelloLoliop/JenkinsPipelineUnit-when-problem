def call() {
    pipeline {
        stages {
            stage("Tests") {
                when {
                    expression {
                        false // nested stage "Unit tests" should be skipped due to this condition
                    }
                }
                parallel {
                    stages {
                        stage("Unit tests") {
                            when {
                                expression {
                                    true
                                }
                            }
                            steps {
                                script {
                                    println('Should not run')
                                }
                            }
                        }
                    }
                }
            }
            stage("Build image") {
                when {
                    expression {
                        true
                    }
                }
                steps {
                    script {
                        println('Should run')
                    }
                }
            }
        }
    }
}
