.\000_intro.md


﻿JavaFX
------
https://github.com/levelp/JavaFX.git

Dependency Injection
--------------------

Принципы DI, реализация вручную, использование Spring:
https://github.com/levelp/dependency_injection

Введение в язык Groovy
----------------------
https://github.com/levelp/groovy

Альтернативные системы сборки
-----------------------------
Ant - https://github.com/stden/JavaAnt
Maven - https://github.com/stden/JavaMaven
Gradle - https://github.com/levelp/JavaGradle

NetBeans
--------

Spring Framework
----------------
* Spring MVC
* Spring ORM
* Spring Security
 * https://github.com/levelp/JavaSpringSecurity

Демонстрация: https://github.com/levelp/JobSitePostgreSQL


.\01_Thread\pom.xml

.\01_Thread\readme.md


﻿JMM. Потоки выполнения. Synchronize, final, volatile
----------------------------------------------------

.\01_Thread\src\main\java\ASimpleThreads.java

Первый поток
Длинный цикл
Пауза: 1 секунда
Ввод строчек
.\01_Thread\src\main\java\Cores.java

.\01_Thread\src\main\java\Main.java

Работа с потоками
-----------------
Остановились
.\01_Thread\src\main\java\ManyThread.java

.\01_Thread\src\main\java\SharedClass.java

.\01_Thread\src\main\java\Thread1.java

.\01_Thread\src\main\java\Thread2.java

String s = Main.strings.remove();
.\01_Thread\src\main\java\ThreadDebug.java

t2.wait();
.\01_Thread\src\main\java\ThreadDemo.java

counter++;
.\01_Thread\src\main\java\ThreadDemo2.java

Первый поток
Второй поток
.\01_Thread\src\main\java\ThreadDemoAtomic.java

.\01_Thread\src\main\java\VolatileDemo.java

private static AtomicInteger x = new AtomicInteger(0);
volatile static int x2 = 0;
Запускаем 2 потока
System.out.println("x changed = " + x);
x.incrementAndGet();
Прерывание своего потока
Thread.currentThread().interrupt();
.\01_Thread\src\test\java\VolatileTest.java

Создаём много потоков
.\01_Thread\src\test\java\vol\DelayWrite.java

.\01_Thread\src\test\java\vol\TestVolatile.java

.\01_Thread\src\test\java\vol\ThreadTest.java

Sleep for a bit so that thread 1 has a chance to start
.\01_Thread\src\test\java\vol\Volatile.java

.\01_Thread\src\test\java\vol\VolatileTest.java

.\02_Atomic\pom.xml

.\02_Atomic\src\main\java\AtomicDemo.java

atomic
------
Классы из пакета java.util.concurrent.atomic обеспечивают
выполнение атомарных операций
``` java
public class AtomicDemo {
    static final Object LOCK = new Object();
    static final AtomicInteger ATOMIC_SUM = new AtomicInteger();
    static final CountDownLatch CDL = new CountDownLatch(100000);
    static int sum = 0;
    static volatile int globalI;
    static int threadCount = 0;

    static {
        AtomicInteger atomicInteger = new AtomicInteger(2);
        AtomicLong atomicLong = new AtomicLong(3232L);
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        // Нет: AtomicByte, AtomicShort, AtomicFloat, AtomicDouble, AtomicString
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{1, 3, 4});
        atomicInteger.addAndGet(10);
        atomicBoolean.set(true);
        atomicBoolean.get();
        atomicLong.addAndGet(100000);
        atomicIntegerArray.addAndGet(1, 10);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            Thread thread = new MyThread();
            thread.start();
        }
        System.out.println("sum = " + sum);
        System.out.println("ATOMIC_SUM = " + ATOMIC_SUM.get());
        System.out.println("globalI = " + globalI);
        System.out.println("wait");
        Thread.sleep(500);
        CDL.await();
        System.out.println("threadCount = " + threadCount);
        System.out.println("sum = " + sum);
        System.out.println("ATOMIC_SUM = " + ATOMIC_SUM.get());
    }

    static void inc() {
        synchronized (LOCK) {
            sum++;
        }
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            threadCount++;
            for (int i = 0; i < 100; ++i) {
                globalI++;
                inc();
                ATOMIC_SUM.incrementAndGet();
                CDL.countDown();
            }
        }
    }
}
```
.\02_Atomic\src\main\java\SyncThread.java

1. Загрузить из памяти
2. Поменять значение
3. Записать в память
Подождём оба потока
Какое же значение переменной?
.\02_Atomic\src\main\java\SyncThread2.java

1. Загрузить из памяти
2. Поменять значение
3. Записать в память
Подождём оба потока
Какое же значение переменной?
.\02_Atomic\src\main\java\SyncThread3.java

1. Загрузить из памяти
2. Поменять значение
3. Записать в память
Подождём оба потока
Какое же значение переменной?
.\02_Atomic\src\main\java\SyncThread4.java

1. Загрузить из памяти
2. Поменять значение
3. Записать в память
Подождём оба потока
Какое же значение переменной?
.\02_Atomic\src\main\java\SyncThread5.java

1. Загрузить из памяти
2. Поменять значение
3. Записать в память
Подождём оба потока
Какое же значение переменной?
.\02_Atomic\src\test\java\AtomicTest.java

.\02_Atomic\src\test\java\SyncronizedTest.java

Страртуем 10000 потоков на increment
Поток пусть поспит случайное время
Подождём теперь 10 секунд
Страртуем 10000 потоков на increment
Подождём теперь 10 секунд
.\03_Properties\pom.xml

.\03_Properties\src\test\java\PropertiesTest.java

Получение несуществующего свойства
.\WebCalc\README.md


Web-калькулятор
===============

Одна форма, две кнопки отправки:
одна для сложения и одна для умножения.

.\WebCalc\pom.xml

.\WebCalc\src\main\java\calc\Calc.java

.\pom.xml

.\webapp\README.md



* Открываем pgAdmin III, создаём БД **webapp**
* Открываем БД в Idea используя следующие настройки:
     * URL: jdbc:postgresql://localhost:5432/webapp



.\webapp\pom.xml

.\webapp\src\main\java\Main.java

Thread.sleep(500);
.\webapp\src\main\java\MainStringBuilder.java

.\webapp\src\main\java\Name.java

.\webapp\src\main\java\minmax\MinMax.java

.\webapp\src\main\java\minmax\MinMaxComparable.java

.\webapp\src\main\java\minmax\MinMaxStatic.java

.\webapp\src\main\java\webapp\Config.java

.\webapp\src\main\java\webapp\WebAppException.java

.\webapp\src\main\java\webapp\model\ContactType.java

.\webapp\src\main\java\webapp\model\Link.java

.\webapp\src\main\java\webapp\model\Organization.java

.\webapp\src\main\java\webapp\model\OrganizationSection.java

.\webapp\src\main\java\webapp\model\Period.java

.\webapp\src\main\java\webapp\model\Resume.java

.\webapp\src\main\java\webapp\model\Section.java

.\webapp\src\main\java\webapp\model\SectionClass.java

.\webapp\src\main\java\webapp\model\SectionType.java

.\webapp\src\main\java\webapp\model\TextSection.java

.\webapp\src\main\java\webapp\sql\ConnectionFactory.java

.\webapp\src\main\java\webapp\sql\DirectConnection.java

Загружаем драйвер для PostgreSQL
"jdbc:postgresql://ec2-54-247-99-244.eu-west-1.compute.amazonaws.com:5432/dap8baaauorm64?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory",
"elfkolfqypggvo", "vmnWGnCVY5jbSD5nrpnYdU-FEd");
.\webapp\src\main\java\webapp\sql\Sql.java

.\webapp\src\main\java\webapp\sql\SqlExecutor.java

.\webapp\src\main\java\webapp\sql\SqlTransaction.java

.\webapp\src\main\java\webapp\storage\AbstractStorage.java

.\webapp\src\main\java\webapp\storage\ArrayStorage.java

return all not null elements
.\webapp\src\main\java\webapp\storage\DataStreamStorage.java

.\webapp\src\main\java\webapp\storage\FileStorage.java

.\webapp\src\main\java\webapp\storage\IStorage.java

.\webapp\src\main\java\webapp\storage\MapStorage.java

.\webapp\src\main\java\webapp\storage\SerializeStorage.java

.\webapp\src\main\java\webapp\storage\SqlStorage.java

Strategy
.\webapp\src\main\java\webapp\storage\XmlStorage.java

.\webapp\src\main\java\webapp\util\DateUtil.java

.\webapp\src\main\java\webapp\util\JaxbParser.java

marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
.\webapp\src\main\java\webapp\util\Util.java

.\webapp\src\main\java\webapp\web\HtmlUtil.java

.\webapp\src\main\java\webapp\web\ResumeServlet.java

Действие по-умолчанию
add first item in every edited item
.\webapp\src\test\java\minmax\MinMaxTest.java

.\webapp\src\test\java\webapp\storage\ArrayStorageTest.java

.\webapp\src\test\java\webapp\storage\DataStreamStorageTest.java

.\webapp\src\test\java\webapp\storage\MapStorageTest.java

.\webapp\src\test\java\webapp\storage\SerializeStorageTest.java

.\webapp\src\test\java\webapp\storage\SqlStorageTest.java

.\webapp\src\test\java\webapp\storage\StorageTest.java

Execute before every test
Tests order is random
.\webapp\src\test\java\webapp\storage\XmlStorageTest.java

