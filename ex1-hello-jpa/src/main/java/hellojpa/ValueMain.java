package hellojpa;

public class ValueMain {
    public static void main(String[] args){
        int a = 10;
        int b = a;
        System.out.println("a == b " + (a==b));

        Address address1 = new Address("city", "street", "zipcode");
        Address address2 = new Address("city", "street", "zipcode");
        System.out.println("addr1 == addr2 " + (address1 == address2));
        System.out.println("addr1 equals addr2 " + address1.equals(address2));

    }
}
