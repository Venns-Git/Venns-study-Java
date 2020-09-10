package Generic;
/*
    定义一个含义泛型的类，模拟Arraylist集合
 */
public class MyGenericClass<E> {
    private E name;

    public E getName() {
        return name;
    }

    public void setName(E name) {
        this.name = name;
    }
}
