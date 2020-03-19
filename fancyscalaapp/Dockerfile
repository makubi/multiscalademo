FROM adoptopenjdk:8-hotspot

COPY target/assembly/fancyscalaapp-1.0-SNAPSHOT-full-tar/ /opt/fancyscalaapp

CMD java -cp "/opt/fancyscalaapp/*" de.kaufhof.ess.itgr.FancyScalaApp