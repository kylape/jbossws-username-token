#!/bin/bash

if [ "$JBOSS_HOME" == "" ]; then
  echo "Please set JBOSS_HOME"
  exit 1
fi

$JBOSS_HOME/bin/add-user.sh -a klape "RedHat13#"
$JBOSS_HOME/bin/add-user.sh -a snoopy "RedHat13#"
echo -e "\nklape=hello" >> $JBOSS_HOME/standalone/configuration/application-roles.properties
echo "snoopy=snoopies" >> $JBOSS_HOME/standalone/configuration/application-roles.properties
