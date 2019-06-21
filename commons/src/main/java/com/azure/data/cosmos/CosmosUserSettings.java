package com.azure.data.cosmos;

import com.azure.data.cosmos.internal.Constants;

import java.util.List;
import java.util.stream.Collectors;

public class CosmosUserSettings extends Resource {
    /**
     * Initialize a user object.
     */
    public CosmosUserSettings() {
        super();
    }

    /**
     * Initialize a user object from json string.
     *
     * @param jsonString the json string that represents the database user.
     */
    public CosmosUserSettings(String jsonString) {
        super(jsonString);
    }

    CosmosUserSettings(ResourceResponse<User> response) {
        super(response.getResource().toJson()); 
    }

    // Converting document collection to CosmosContainerSettings
    CosmosUserSettings(User user){
        super(user.toJson());
    }

    /**
     * Gets the self-link of the permissions associated with the user.
     *
     * @return the permissions link.
     */
    public String getPermissionsLink() {
        String selfLink = this.selfLink();
        if (selfLink.endsWith("/")) {
            return selfLink + super.getString(Constants.Properties.PERMISSIONS_LINK);
        } else {
            return selfLink + "/" + super.getString(Constants.Properties.PERMISSIONS_LINK);
        }
    }

    public User getV2User() {
        return new User(this.toJson());
    }

    static List<CosmosUserSettings> getFromV2Results(List<User> results) {
        return results.stream().map(CosmosUserSettings::new).collect(Collectors.toList());
    }
}