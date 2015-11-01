#Dockerfile
FROM centos:6.7
MAINTAINER pauldeng <pauldeng100@163.com>

#Install base software
RUN yum -y install curl wget 

#Change source
RUN curl https://raw.githubusercontent.com/pauldeng100/plus-one/master/docker/aliyun-centos6-yum.sh | sh

#Install other common softwares
RUN yum -y install passwd vim iftop htop man glibc.i686 wget tar wget unzip python-setuptools dos2unix openssh-server telnet which sudo libaio*\
    && yum -y clean all
	
#Change the root password
RUN echo 'root:123abcABC' | chpasswd

#Set the ssh connection
RUN ssh-keygen -t rsa -f /etc/ssh/ssh_host_rsa_key \
    && sed -i 's/#PermitRootLogin yes/PermitRootLogin yes/' /etc/ssh/sshd_config \
    && sed -ri 's/UsePAM yes/#UsePAM yes/g' /etc/ssh/sshd_config \
    && sed -ri 's/#UsePAM no/UsePAM no/g' /etc/ssh/sshd_config
    
#Create the user docker
RUN useradd docker \
   && echo "docker:docker" | chpasswd 

#Add the created user into sudoer
RUN echo "docker        ALL=(ALL)       NOPASSWD: ALL" >> /etc/sudoers
CMD /usr/sbin/sshd -D
#End

