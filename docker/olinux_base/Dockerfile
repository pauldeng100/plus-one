#Dockerfile
FROM oracle/oraclelinux:6.6
MAINTAINER pauldeng <pauldeng100@163.com>

RUN yum -y install passwd vim wget tar unzip python-setuptools  openssh-server telnet which sudo
	
RUN echo 'root:123abcABC' | chpasswd

RUN easy_install supervisor

RUN ssh-keygen -t rsa -f /etc/ssh/ssh_host_rsa_key \
    && sed -i 's/#PermitRootLogin yes/PermitRootLogin yes/' /etc/ssh/sshd_config \
    && sed -ri 's/UsePAM yes/#UsePAM yes/g' /etc/ssh/sshd_config \
    && sed -ri 's/#UsePAM no/UsePAM no/g' /etc/ssh/sshd_config
    
RUN useradd docker \
   && echo "docker:docker" | chpasswd 

RUN echo "docker        ALL=(ALL)       NOPASSWD: ALL" >> /etc/sudoers

ADD supervisor.conf /etc/supervisor.conf

CMD ["supervisord", "-c", "/etc/supervisor.conf"]
#End

