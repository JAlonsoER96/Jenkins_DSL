job('ejemplo2-job-dsl'){
  description('DSL de ejemplo para curso de Jenkins')
  scm{
    git('https://github.com/JAlonsoER96/Jenkins_DSL.git', 'master') { node ->
      node / gitConfigName('JAlonsoER96')
      node / gitConfigEmail('al221411324@gmail.com')
    }
  }
  parameters{
    stringParam('nombre',defaultValue='Alonso',description='Párametro de cadena para el job booleano')
    choiceParam('planeta',['Mercurio','Venus','Tierra','Marte','Jupiter','Saturno','Urano','Neptuno','Pluton'])
    booleanParam('agente',false)
    
  }
  triggers{
    cron('H/7 * * * *')
    githubPush()
  }
  steps{
    shell('bash jobscript.sh')
  }
  publishers{
    mailer('j.a.espinares.romero@hotmail.com', true, true)
    slackNotifier{
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }
    
}