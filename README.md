## CookiersLib 1.0.4

It is a library containing the most frequently repeated functions during my adventure with writing plugins.

## Features

#### Inventory utilities:
- check if player has empty slot in inventory
- find index of first empty slot in inventory
- find index of itemStack in players inventory

#### Position utilities:
- get set of players that are in radius of passed location

#### Item creator:
- create custom item

#### Item manager:
- apply enchantments to item meta
- add namespaced key to item meta
- get namespaced key value from item
- check if item has specific value for specific namespaced key
- set item display name
- set empty item display name 

#### Validation utilities:
- check if lore is valid
- check if the string value is valid
- check if double is positive or zero
- check if int is positive or zero
- check if the sound is valid
- check if the args have correct quantity

#### Commands Utilities
- register commands with tab completer

## How to use
Just paste this code into your project and you will have access to all utilities

CookiersLib cookiersLib = new CookiersLib();

### How to use utilities?
eg. i want to get to use function for creating item so after lib initialization i can simply write:
<br>
<br>
cookiersLib.getItemCreator().createCustomItem(...);

### Known issues
If you have problem with server error "Plugin is already initialized" take care of your maven shading.
Simply add this code beetwen <execution> brackets in your maven-shade-plugin in pom.xml:

                      <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                        <configuration>
                            <filters>
                                <filter>
                                    <artifact>pawel.cookier.ignaczak:CookiersLib</artifact>
                                    <excludes>
                                        <exclude>META-INF/MANIFEST.MF</exclude>
                                        <exclude>plugin.yml</exclude>
                                    </excludes>
                                </filter>
                            </filters>
                        </configuration>
                    </execution>

                    
### Licence: MIT

#### How to add (click on icon below) 
versions below 1.0.3 may produce some issues
<br>
<br>
[![](https://jitpack.io/v/C00kier/CookiersLib.svg)](https://jitpack.io/#C00kier/CookiersLib)
