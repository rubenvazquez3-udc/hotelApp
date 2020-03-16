-- Indexes for primary keys have been explicitly created.

DROP TABLE OrderItem;
DROP TABLE OrderTable;
DROP TABLE ShoppingCartItem;
DROP TABLE ShoppingCart;
DROP TABLE Product;
DROP TABLE Category;
DROP TABLE User;

CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(60) COLLATE latin1_bin NOT NULL,
    password VARCHAR(60) NOT NULL, 
    firstName VARCHAR(60) NOT NULL,
    lastName VARCHAR(60) NOT NULL, 
    email VARCHAR(60) NOT NULL,
    role TINYINT NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT UserNameUniqueKey UNIQUE (userName)
) ENGINE = InnoDB;

CREATE INDEX UserIndexByUserName ON User (userName);

CREATE TABLE Category (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    CONSTRAINT CategoryPK PRIMARY KEY (id),
    CONSTRAINT CategoryNameUniqueKey UNIQUE (name)
) ENGINE = InnoDB;

CREATE INDEX CategoryIndexByName ON Category (name);

CREATE TABLE Product (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(2000) NOT NULL,
    price DECIMAL(11, 2) NOT NULL,
    categoryId BIGINT NOT NULL,
    CONSTRAINT ProductPK PRIMARY KEY (id),
    CONSTRAINT ProductCategoryIdFK FOREIGN KEY(categoryId)
        REFERENCES Category (id)
) ENGINE = InnoDB;

CREATE INDEX ProductIndexByName ON Product (name);

CREATE TABLE ShoppingCart (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userId BIGINT NOT NULL,
    CONSTRAINT ShoppingCartPK PRIMARY KEY (id),
    CONSTRAINT ShoppingCartUserIdFK FOREIGN KEY(userId)
        REFERENCES User (id)
) ENGINE = InnoDB;

CREATE TABLE ShoppingCartItem (
    id BIGINT NOT NULL AUTO_INCREMENT,
    productId BIGINT NOT NULL,
    quantity SMALLINT NOT NULL,
    shoppingCartId BIGINT NOT NULL,
    CONSTRAINT ShoppingCartItemPK PRIMARY KEY (id),
    CONSTRAINT ShoppingCartItemProductIdFK FOREIGN KEY(productId)
        REFERENCES Product (id),
    CONSTRAINT ShoppingCartItemShoppingCartIdFK FOREIGN KEY(shoppingCartId)
        REFERENCES ShoppingCart (id)
) ENGINE = InnoDB;

CREATE TABLE OrderTable (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userId BIGINT NOT NULL,
    date DATETIME NOT NULL,
    postalAddress VARCHAR(200) NOT NULL,
    postalCode VARCHAR(20) NOT NULL,
    CONSTRAINT OrderPK PRIMARY KEY (id),
    CONSTRAINT OrderUserIdFK FOREIGN KEY(userId)
        REFERENCES User (id)
) ENGINE = InnoDB;

CREATE TABLE OrderItem (
    id BIGINT NOT NULL AUTO_INCREMENT,
    productId BIGINT NOT NULL,
    productPrice DECIMAL(11, 2) NOT NULL,
    quantity SMALLINT NOT NULL,
    orderId BIGINT NOT NULL,
    CONSTRAINT OrderItemPK PRIMARY KEY (id),
    CONSTRAINT OrderItemProductIdFK FOREIGN KEY(productId)
        REFERENCES Product (id),
    CONSTRAINT OrderItemOrderIdFK FOREIGN KEY(orderId)
        REFERENCES OrderTable (id)
) ENGINE = InnoDB;

