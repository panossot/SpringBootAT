# SpringBootAT

## SpringBootAT is an implementation of the AT Structures for Spring Boot

### A PROJECT UNDER THE ΙΔΕΑ STATEMENT

Steps to execute :

1. (Build Spring Boot -- also Spring Boot Framework, spring-data-build, io.micrometer, reactor, spring-amqp, spring-batch, etc if needed -- in order to produce the Spring Boot artifacts)
2. Copy the artifacts of spring-boot/spring-boot-project/spring-boot-dependencies/build/local-m2-repository to your local maven repository
3. export SPRINGBOOT_BRANCH_VERSION='version of Spring Boot branch'
4. export SPRINGFRAMEWORK_BRANCH_VERSION='version of Spring Boot Framework branch'
5. mvn clean install -Dmaster

Note : This AT starts from version 2.1.0.M3

## License

Code distributed under [ASL 2.0](LICENSE.TXT)(licenses of the Active MQ test sources) and [GNU Lesser General Public License Version 2.1](http://www.gnu.org/licenses/lgpl-2.1-standalone.html) (for the repo).
