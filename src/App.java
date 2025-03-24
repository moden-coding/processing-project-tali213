import processing.core.*;

public class App extends PApplet {
    int rectX = 225; // declaring the chicken's variables
    int rectY = 470;
    int rectWidth = 30;
    int rectHeight = 30;
    int speed = 10;

    int pinkrectX = 0; // declaring the pink car's variables
    int pinkrectY = 170;
    int pinkrectWidth = 60;
    int pinkrectHeight = 50;

    int yellowrectX = 450; // declaring the yellow car's variables
    int yellowrectY = 255;
    int yellowrectWidth = 60;
    int yellowrectHeight = 50;

    int stage = 0; // there are 3 stages
    int score = 0; // for keeping track of the score
    boolean moveUp = false, moveDown = false; // for controlling the car
    boolean invincible = false; // for testing the car without it being hit
    boolean scorable = true; // for making the car only able to score at a specific point

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
    }

    public void settings() {    // setting the size of the frame
        size(500, 500);
    }

    public void draw() {
        if (stage == 0) { // start screen
            background(40, 100, 200);
            fill(255, 255, 255);
            textSize(35);
            text("DON'T GET HIT BY THE CAR", 50, 200);
            textSize(20);
            text("USE ARROW KEYS TO MOVE",140,250);
            textSize(25);
            text("CLICK ANYWHERE TO START", 110, 300);
        }

        else if (stage == 1) { // game play screen
            drawBackground();

            if (invincible == false && (touching(pinkrectX, pinkrectY) || touching(yellowrectX, yellowrectY))) {    // if either of the cars collide with the chicken and the "i" button isnt pressed, lose screen appears and car resets
                stage = 2;
                resetCar();
            } if (rectY < 145 && scorable == true) {  // if you cross the road the score increases by 1 at this certain point, and scorable resets
                score++;
                scorable = false;
            } if (rectY <= 0) {   // when the chicken reaches the top of the screen, it resets to the bottom
                resetCar();
                scorable = true;
            }
            pinkCar();  // draw pink car
            
            if (pinkrectX > 500) {  // only allows it to move within the frame
                pinkrectX = (int) random(-100, 0);  // car starts at a random point between these points off of the screen so the cars come at different times
            }
            yellowCar();    // draw yellow car

            if (yellowrectX < 0) {  // only allows it to move within the frame
                yellowrectX = (int) random(600, 500);   // car has a random starting point off screen so the cars come at different times
            }
            drawChicken();  // drawing the chicken square 

            if (moveUp && rectY > 0)    // speed for the moving the chicken up
                rectY -= speed;
            if (moveDown && rectY + rectHeight < height)    // speed for moving the chicke down
                rectY += speed;
        } 
            else if (stage == 2) {  // lose screen
            background(40, 100, 200);
            fill(255, 255, 255);
            textSize(40);
            text("YOU LOSE", 160, 200);
            textSize(30);
            text("SCORE: " + score, 190, 250);
            text("CLICK ANYWHERE TO RESTART", 60, 300); // after this the mouse is pressed, which autommatically calls that method to reset the game
        }
    }

    public void pinkCar(){  // declaring the pink car and its speed
            stroke(0);  
            strokeWeight(1);
            fill(255, 105, 180);
            rect(pinkrectX, pinkrectY, pinkrectWidth, pinkrectHeight);
            pinkrectX += speed;
    }

    public void yellowCar(){    // declaring the yellow car and its speed
            stroke(0);  
            strokeWeight(1);
            fill(255, 223, 0);
            rect(yellowrectX, yellowrectY, yellowrectWidth, yellowrectHeight);
            yellowrectX -= speed;
    }

    public void drawChicken(){  // declaring the chicken in the game
        stroke(0);  
        strokeWeight(1);
        fill(50, 110, 255);
        rect(rectX, rectY, rectWidth, rectHeight);
    }
    public void resetCar() {    // resets the car back to starting position
        rectX = 225;
        rectY = 470;
    }

    public void drawBackground() {  // creates the road and grass on the play screen. also makes the score count appear in the corner
        background(100, 100, 100);  // grey on the road

        stroke(255, 255, 255);  // white lines on the side of the road
        strokeWeight(8);
        line(0, 150, 500, 150);
        line(0, 325, 500, 325);

        stroke(255, 216, 0);    // yellow lines in the middle of the road
        strokeWeight(2);
        line(0, 235, 500, 235);
        line(0, 240, 500, 240);

        stroke(60, 179, 113);   // green grass on btoh sides of the road
        fill(60, 179, 113);
        rect(0, 0, 500, 145);
        rect(0, 330, 500, 170);

        fill(255, 255, 255);    // score count in the top right corner
        textSize(25);
        text("Score: " + score, 30, 35);
    }

    public boolean touching(int pinkrectX, int pinkrectY) { // declaring variables for car and chicken collision
        int carLeft = pinkrectX;
        int carRight = pinkrectX + yellowrectWidth;
        int carTop = pinkrectY;
        int carBottom = pinkrectY + yellowrectHeight;

        int ChickenLeft = rectX;
        int ChickenRight = rectX + rectWidth;
        int ChickenTop = rectY;
        int ChickenBottom = rectY + rectHeight;

        if (carLeft < ChickenRight) {   // checking to see if the cars and chicken are overlapping
            if (carRight > ChickenLeft) {
                if (carBottom > ChickenTop) {
                    if (carTop < ChickenBottom) {
                        return true;    // if all of these are true, a car and the chicken have collided
                    }
                }
            }
        }
        return false;   // if any of these are false, the car and the chicken have not collided
    }

    public void mousePressed() {    // when this is called, depending on the screen it is currently on it will reset to a specifc other screen
        if (stage == 0) {
            stage = 1;
        }
        if (stage == 2) {
            stage = 1;
            score = 0;  // will also reset the count in this instance because the game is starting over

        }
    }

    public void keyPressed() {
        if (key == 'i') {   // key used to make the chicken unable to die so I can test parts of the code
            invincible = !invincible;
        }
        if (keyCode == UP)  // controlling the chickens up and down movement, if either of the arrow keys are pressed the chicken moves
            moveUp = true;
        if (keyCode == DOWN)
            moveDown = true;
    }

    public void keyReleased() { // when the up and down arrow keys are released it stops the chicken from moving anymore
        if (keyCode == UP)
            moveUp = false;
        if (keyCode == DOWN)
            moveDown = false;
    }
}
