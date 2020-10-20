**job4j_mail**
[![Build Status](https://travis-ci.org/amasterenko/job4j_mail.svg?branch=master)](https://travis-ci.org/amasterenko/job4j_mail)
[![codecov](https://codecov.io/gh/amasterenko/job4j_mail/branch/master/graph/badge.svg?token=HSC6HUZR4Q)](https://codecov.io/gh/amasterenko/job4j_mail)

Почта. [#184458]
Имеется n пользователей, каждому из них соответствует список email-ов
(всего у всех пользователей m email-ов).
Например:
user1 ->xxx@ya.ru,foo@gmail.com,lol@mail.ru
user2 ->foo@gmail.com,ups@pisem.net
user3 ->xyz@pisem.net,vasya@pupkin.com
user4 ->ups@pisem.net,aaa@bbb.ru
user5 ->xyz@pisem.net

Считается, что если у двух пользователей есть общий email, значит это
один и тот же пользователь. Требуется построить
и реализовать алгоритм, выполняющий слияние пользователей. На выходе
должен быть список пользователей с их email-ами (такой же как на
входе).
В качестве имени объединенного пользователя можно брать любое из
исходных имен. Список email-ов пользователя должен содержать только
уникальные email-ы.
Параметры n и m произвольные, длина конкретного списка email-ов никак
не ограничена.

Как запустить приложение.
1. Скопировать проект
1. Установить Maven
2. Перейти в каталог программы, содержащий файл pom.xml и выполнить mvn clean install.
3. Выполнить java -jar target/job4j_mail.jar filename, где filename - путь к файлу с входными данными
4. В папке с программой появится файл result.txt - файл с результатом работы