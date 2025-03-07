## CookiersLib 1.0.9

It is a library containing the most frequently repeated functions during my adventure with writing plugins.

## Features

#### Inventory utilities:
- check if player has empty slot in inventory
- find index of first empty slot in inventory
- find index of itemStack in players inventory
- populate inventory with ItemStack

#### Position utilities:
- get set of players that are in radius of passed location

#### Item creator:
- create custom item

#### Item manager:
- apply enchantments to item meta
- apply enchantments to item stack
- add namespaced key to item meta
- add namespaced key to item stack
- get namespaced key value from item
- check if item has specific value for specific namespaced key
- set item display name
- set empty item display name
- set lore to item stack
- check if 2 items has same enchantments
- check if 2 lists contains same itemstacks

#### Validation utilities:
- check if lore is valid
- check if the string value is valid
- check if double is positive or zero
- check if int is positive or zero
- check if the sound is valid
- check if the args have correct quantity

#### Commands Utilities
- register commands with tab completer
- register commands without tab completer
- register command dynamically without using plugin.yml

#### Yaml config utilities (FileConfiguration Object utilities)
- function for colorize String via config with usage of "&" sign
- function for colorize List of Stings via config with usage of "&" sign

#### World Utilities
- check if player is in world with keepInventory set true

## Models
- Custom config model

## How to use
Just paste this code into your project and you will have access to all utilities

CookiersLib cookiersLib = new CookiersLib();

### How to use utilities?
eg. i want to get to use function for creating item so after lib initialization i can simply write:
<br>
<br>
cookiersLib.getItemCreator().createCustomItem(...);

### How to use models?
CustomConfig config = new CustomConfig(plugin, fileName);

It will create config with fileName into plugin folder.
Simply add fileName.yaml to project resources and you are ready to go!

### Licence: MIT

#### How to add (click on icon below) 
versions below 1.0.3 may produce some issues
<br>
<br>
[![](https://jitpack.io/v/C00kier/CookiersLib.svg)](https://jitpack.io/#C00kier/CookiersLib)
