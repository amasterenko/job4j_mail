## Emails    
[![Build Status](https://travis-ci.org/amasterenko/job4j_mail.svg?branch=master)](https://travis-ci.org/amasterenko/job4j_mail)
[![codecov](https://codecov.io/gh/amasterenko/job4j_mail/branch/master/graph/badge.svg?token=HSC6HUZR4Q)](https://codecov.io/gh/amasterenko/job4j_mail)  
---
#### Задача  
Имеется n пользователей, каждому из них соответствует список email-ов
(всего у всех пользователей m email-ов).  



Считается, что если у двух пользователей есть общий email, значит это
один и тот же пользователь.  
Требуется построить и реализовать алгоритм, выполняющий слияние пользователей. На выходе
должен быть список пользователей с их email-ами (такой же, как на
входе).  
В качестве имени объединенного пользователя можно брать любое из
исходных имен. Список email-ов пользователя должен содержать только
уникальные email-ы.  
Параметры n и m произвольные, длина конкретного списка email-ов никак
не ограничена.  

_Пример входных данных:_     
user1,xxx@ya.ru,foo@gmail.com,lol@mail.ru
user2,foo@gmail.com,ups@pisem.net
user3,xyz@pisem.net,vas@tng.com
user4,ups@pisem.net,aaa@bbb.ru
user5,xyz@pisem.net

_Выходные данные:_    
user1,xxx@ya.ru,foo@gmail.com,lol@mail.ru,ups@pisem.net,aaa@bbb.ru
user3,xyz@pisem.net,vas@tng.com


####

#### Как запустить приложение.  
1. Скопировать проект.  
2. Установить Maven.  
3. Перейти в каталог проекта, содержащий файл _pom.xml_ и выполнить команду ```mvn clean install```.    
4. Выполнить java -jar target/job4j_mail.jar filename, где filename - путь к текстовому файлу с входными данными.  
Формат строк файла:```user,email1,...,emailM```  

5. В папке проекта появится файл с результатами работы _result.txt_.  
