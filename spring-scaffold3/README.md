# Spring 3.x scaffold

#### 기본 구성

- Spring 3.0.8
- gradle (7+)
- jdk 17
- H2, JPA, QueryDSL

#### 메모

- JPA, QueryDSL 예시 작성. Critika는 타입체크가 안되서 JPA + QueryDSL이 좋아 보인다.

#### 참조

**1. IntelliJ에서 QueryDSL Annotation Processing 활성화**

Preferences (또는 Settings)로 이동
Build, Execution, Deployment > Compiler > Annotation Processors로 이동
Enable annotation processing (애너테이션 프로세싱 활성화) 옵션을 선택

**2. QueryDSL 관련 설정**

```groovy
def generated = 'src/main/generated'

tasks.named('test') {
	useJUnitPlatform()
}

tasks.withType(JavaCompile) {
	options.getGeneratedSourceOutputDirectory().set(file(generated))
}

sourceSets {
	main.java.srcDirs += [ generated ]
}

clean {
	delete file(generated)
}
```