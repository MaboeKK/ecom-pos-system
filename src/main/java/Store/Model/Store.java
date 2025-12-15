/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Store.Model;

import lombok.*;
@Getter@Setter@AllArgsConstructor@NoArgsConstructor@Builder

public class Store {

    private String STORE_LOCATION_PROVINCE;
    private String STORE_LOCATION_CITY;
    private String STORE_LOCATION_ADDRESS;
    private String MANAGER_ID;
    private int store_id;
    private String store_name;

    public Store(String STORE_LOCATION_PROVINCE, String STORE_LOCATION_CITY, String STORE_LOCATION_ADDRESS, String MANAGER_ID) {
        this.STORE_LOCATION_PROVINCE = STORE_LOCATION_PROVINCE;
        this.STORE_LOCATION_CITY = STORE_LOCATION_CITY;
        this.STORE_LOCATION_ADDRESS = STORE_LOCATION_ADDRESS;
        this.MANAGER_ID = MANAGER_ID;
    }

    public Store(String STORE_LOCATION_PROVINCE, String STORE_LOCATION_CITY, String STORE_LOCATION_ADDRESS) {
        this.STORE_LOCATION_PROVINCE = STORE_LOCATION_PROVINCE;
        this.STORE_LOCATION_CITY = STORE_LOCATION_CITY;
        this.STORE_LOCATION_ADDRESS = STORE_LOCATION_ADDRESS;
    }
}