package webapp.storage;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Пример на сохранение/загрузку объекта в XML файл
 */
public class JAXBTest {
    static class Point {
        public double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public Point() {
        }
    }

    static class Line {
        public Point p1, p2;
    }

    @XmlRootElement(name = "myClass")
    static class MyClass {
        public int x;

        public int[] ints = new int[]{2, 3, 20, 20};

        public String name = "MyClass name";

        String name2 = "name2";

        public String getName2() {
            return name2;
        }

        public void setName2(String name2) {
            this.name2 = name2;
        }

        public Line line = new Line();

        MyClass() {
            line.p1 = new Point(1, 2);
            line.p2 = new Point(3, 4);
        }
    }

    private static Marshaller marshaller = null;
    private static Unmarshaller unmarshaller = null;

    static {
        try {
            JAXBContext ctx = JAXBContext.newInstance(MyClass.class);
            marshaller = ctx.createMarshaller();
            unmarshaller = ctx.createUnmarshaller();
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testSaveLoad() throws JAXBException {
        // Создаём объект для примера
        MyClass myClass = new MyClass();
        myClass.x = 11;

        String xmlFileName = "myClass.xml";
        marshaller.marshal(myClass, new File(xmlFileName));

        MyClass loaded = (MyClass)
                unmarshaller.unmarshal(new File(xmlFileName));
        assertEquals(myClass.x, loaded.x);
    }
}
