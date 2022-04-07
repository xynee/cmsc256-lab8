package cmsc256;

public class Dog implements Comparable<Dog>{
    private String dogName;
    private int count;

    public Dog(String dogName, int count){
        setDogName(dogName);
        setCount(count);
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString(){
        return dogName + " is registered " + count + " times.";
    }

    @Override
    public int compareTo(Dog dog){
        return this.getDogName().compareTo(dog.getDogName());
    }
}
