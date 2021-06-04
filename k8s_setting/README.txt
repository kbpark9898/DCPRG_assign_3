0. 본 압축파일 구성

DCPRG_assign_2 : 본 과제에 사용된 서버 소스코드 (스프링부트 프로젝트)

assign_db.yaml : 본 과제에 사용된 DB deployment 및 pod  제작 yaml file

assign_server.yaml : 본 과제에 사용된 springboot server deployment 및 pod 제작 yaml file

2016104122.txt : 서버/DB 이미지가 업로드된 docker hub URL / 서버 소스코드 GitHub URL

README.txt : 본 과제물을 실행시키기 위한 정보를 담은 설명파일


1. 개요

이전 과제에서 사용된 서버와 DB 도커 이미지를 docker hub로부터 불러와서 그대로 사용했습니다.

assign_server.yaml 파일을 이용해 server deployment를 제작했습니다. deployment name과 service(nodeport) name은 모두 my-server로 자동 생성됩니다.
또한 assign_db.yaml 파일을 이용해 mysql database  deployment를 제작했습니다. deployment name은 my-db 이며, service(nodeport) name은 database 입니다.

2. 실행방법

- minikube를 실행합니다. (과제 수행시 실행 환경 : minikube start --vm-driver=virtualbox)

- kubectl apply -f assign_server.yaml --record 명령어를 이용해 server deployment 및 NodePort service를 생성합니다.

- kubectl apply -f assign_db.yaml --record 명령어를 이용해 mysql deployment 및 NodePort service를 생성합니다.

- kubectl get all 명령어를 이용해 my-db deployment가 생성한 my-db pod의 이름을 가져옵니다.

- kubectl exec -it {my-db pod name} bash 명령어를 통해 my-db 컨테이너에 접속합니다.

- mysql -u root -p 명령어를 통해 mysql 서비스에 접속합니다. (패스워드 : 123)

- use boooardgame 명령어를 통해 db를 전환합니다.

- create table posts (id bigint not null auto_increment, created_data datetime(6), modified_data datetime(6), author varchar(255), content TEXT not null, title varchar(500) not null, primary key (id)) engine=InnoDB;  명령어를 실행하여 테이블을 생성합니다.

- local 콘솔로 돌아와서 kubectl get all 명령어를 이용해 my-server deployment가 생성한 my-server pod의 이름을 가져옵니다.

- kubectl exec -it {my-server pod name} /bin/sh 명령어를 통해 server가 실행된 컨테이너에 접속합니다.

- cd /DCPRG_assign_2/build/libs 명령어를 통해 경로를 전환합니다.

- java -jar BoooardGame-0.0.1-SNAPSHOT.jar 명령어를 통해 프로젝트를 빌드합니다.

- kubectl get all 명령어를 사용해 my-server에 해당하는 NodePort service 의 외부 접속 포트를 확인합니다.
  (32001:XXXXX의 형태로 매핑되어있으며, XXXXX에 해당하는 포트번호를 가져옵니다.)

- kubectl cluster-info 명령어를 사용해 k8s master ip를 가져옵니다.

- 로컬의 브라우저로 {k8s master ip}:{위에서 가져온 my-server NodePort service port number} 에 접속하여 게시글을 작성하고, 불러와지는것을 확인합니다.


