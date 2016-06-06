package com.jdkoder.roundkeeper.model.health;

import java.util.Map;

public class HealthEffect {

    /** The name of this health effect **/
    String name;

    /** This is a map of attributes effected and the modifiers that affect them. **/
    Map<String /*Attribute*/, Integer /*Modifier*/> modifierMap;

    /** A nullifier will effectively remove the health effect object from the set of effects on an actor. **/
    String nullifier;
}
