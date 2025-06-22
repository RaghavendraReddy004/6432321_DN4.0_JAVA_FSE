import java.util.*;

interface Command {
    void execute();
}

class Light {
    public void turnOn() {
        System.out.println("Light is ON");
    }

    public void turnOff() {
        System.out.println("Light is OFF");
    }
}

class Fan {
    public void turnOn() {
        System.out.println("Fan is ON");
    }

    public void turnOff() {
        System.out.println("Fan is OFF");
    }
}

class TV {
    public void turnOn() {
        System.out.println("TV is ON");
    }

    public void turnOff() {
        System.out.println("TV is OFF");
    }
}

class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.turnOff();
    }
}

class FanOnCommand implements Command {
    private Fan fan;

    public FanOnCommand(Fan fan) {
        this.fan = fan;
    }

    public void execute() {
        fan.turnOn();
    }
}

class FanOffCommand implements Command {
    private Fan fan;

    public FanOffCommand(Fan fan) {
        this.fan = fan;
    }

    public void execute() {
        fan.turnOff();
    }
}

class TVOnCommand implements Command {
    private TV tv;

    public TVOnCommand(TV tv) {
        this.tv = tv;
    }

    public void execute() {
        tv.turnOn();
    }
}

class TVOffCommand implements Command {
    private TV tv;

    public TVOffCommand(TV tv) {
        this.tv = tv;
    }

    public void execute() {
        tv.turnOff();
    }
}

class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

public class CommandPatternExample {
    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl();

        Light livingRoomLight = new Light();
        Fan ceilingFan = new Fan();
        TV smartTV = new TV();

        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);

        Command fanOn = new FanOnCommand(ceilingFan);
        Command fanOff = new FanOffCommand(ceilingFan);

        Command tvOn = new TVOnCommand(smartTV);
        Command tvOff = new TVOffCommand(smartTV);

        System.out.println("Turning on the light:");
        remote.setCommand(lightOn);
        remote.pressButton();

        System.out.println("Turning off the light:");
        remote.setCommand(lightOff);
        remote.pressButton();

        System.out.println("Turning on the fan:");
        remote.setCommand(fanOn);
        remote.pressButton();

        System.out.println("Turning off the fan:");
        remote.setCommand(fanOff);
        remote.pressButton();

        System.out.println("Turning on the TV:");
        remote.setCommand(tvOn);
        remote.pressButton();

        System.out.println("Turning off the TV:");
        remote.setCommand(tvOff);
        remote.pressButton();
    }
}