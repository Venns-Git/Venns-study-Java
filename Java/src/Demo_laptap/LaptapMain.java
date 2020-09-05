package Demo_laptap;

public class LaptapMain {

    public static void main(String[] args) {
        //创建一个笔记本电脑
        Computer computer = new Computer();
        //开机
        computer.powerOn();

        //准备一个鼠标，供电脑使用
        //Mouse mouse = new Mouse();
        //首先进行向上转型
        USB usbMouse = new Mouse(); //多态写法
        //参数是usb类型，我正好传递进去的就是usb鼠标
        computer.usbDevice(usbMouse);

        //创建一个usb键盘
        Keyboard keyboard = new Keyboard();//没有使用多态写法
        computer.usbDevice(keyboard);

        //关机
        computer.powerOff();
    }
}
