doc:
	mvn javadoc:javadoc
build:
	mvn assembly:assembly
buff:
	echo "\033[32m\n[buff]\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\033[0m" && \
	java \
	-Xmx200M \
	-Xms200M \
	-Xmn150M \
	-XX:SurvivorRatio=4 \
	-XX:+UseAdaptiveSizePolicy \
	-XX:+AlwaysPreTouch \
	-cp target/id-version-jar-with-dependencies.jar \
	buff.x.Deliver
steam:
	echo "\033[32m\n[steam]\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\033[0m" && \
	java \
	-Xmx200M \
	-Xms200M \
	-Xmn150M \
	-XX:SurvivorRatio=4 \
	-XX:+UseAdaptiveSizePolicy \
	-XX:+AlwaysPreTouch \
	-cp target/id-version-jar-with-dependencies.jar \
	steam.x.Server
