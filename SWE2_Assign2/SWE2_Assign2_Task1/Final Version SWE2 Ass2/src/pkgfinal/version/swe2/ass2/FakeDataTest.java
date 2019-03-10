/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.version.swe2.ass2;

/**
 *
 * @author A_Hawwas
 */
public class FakeDataTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Faker faker = new Faker();
 
        String streetName = faker.address().streetName();
        String number = faker.address().buildingNumber();
        String city = faker.address().city();
        String country = faker.address().country();

        System.out.println(String.format("%s\n%s\n%s\n%s",
          number,
          streetName,
          city,
          country));
            }
    
}

