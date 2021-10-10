HOME := $(if $(HOME),$(HOME),$(shell echo $HOME))
M2 := $(if $(M2),$(M2),$(HOME)/.m2)
DOCKER_IMAGE := $(if $(DOCKER_IMAGE),$(DOCKER_IMAGE),maven:3.6-jdk-8-alpine)
PWD := $(shell pwd)

.PHONY: dist

define docker_mvn
	@docker run --rm -i \
		-v $(PWD):/src \
		-v $(M2):/root/.m2 \
		-w /src \
		$(DOCKER_IMAGE) \
		mvn $(1)
endef

default: docker-install docker-test docker-javadoc

package:
	@mvn clean package

release:
	@mvn clean release:prepare release:perform

docker-package:
	$(call docker_mvn,clean package)

docker-install:
	$(call docker_mvn,install -DskipTests=true -Dmaven.javadoc.skip=true -B -V)

docker-test:
	$(call docker_mvn,test -B)

docker-javadoc:
	$(call docker_mvn,clean javadoc:javadoc -B)
