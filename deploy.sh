heroku plugins:install heroku-cli-deploy
heroku war:deploy kakscalculator.war --app kakscalculator
heroku ps:scale web=1 --app kakscalculator

