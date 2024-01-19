# sde-project

This is my SDE project, originally my teammates was Damyan(damyna03 on github), but we faced a dissagreement early on and decided to split up.


I have developed a text-based combat simulation in which players can choose a character class like Mage, Marksman, Fighter, Tank, Assassin, and Monster along with unique combat stats. I designed the code of the game in a modular and extensible way that would be easy to expand and maintain in the future while keeping it organized with various design patterns.

I applied the Abstract Factory Pattern in the PlayerFactory class to create a single interface that will be used to construct different player types. The Singleton Pattern guarantees having only one instance of the PlayerFactory throughout the application and only one access point. I used the Factory Method Pattern for every player and character type, encapsulating within the getPlayer method of PlayerFactory all the creation logic.

The Proxy Pattern is evident in the PlayerProxy class that gets access to the real player object with an added layer of control or additional functionality. MonsterAdapter uses the Adapter Pattern to allow an external Monster class, with different fields of that of the player class. Using it I can add the monster class(as in playable character) easily into the game.

The Template Method Pattern influences the structure of the game through the abstract Player class that defines the template for characters' actions. Each player type extends this base class, providing specific implementations for these behaviors.

I also use the Command Pattern when handling the attacks of each player. The attack method takes an action and its execution, as done in the command pattern where commands are objects that take an action and its parameters.