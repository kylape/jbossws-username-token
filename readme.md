#JBossWS WS-Security Username Token Example

An example of the recommended way to configure JBossWS/CXF such that the
UsernameToken Profile is implemented.

Before deploying, you should set up the `other` security domain by running the
script `configure-users.sh`.  This will add the user `klape` with the role
`hello` and the user `snoopy` with the role `snoopies`.  (This is based on a
stock version of EAP, so you may want to use the script as a guide (though this
would likely require changes to the endpoint, too)).

Build and deploy: `mvn clean install jboss-as:deploy`
Test: `mvn exec:exec`

Requires use of JBoss EAP 6 BOM (only available in 6.2+).
