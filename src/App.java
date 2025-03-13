import processing.core.*;

public class App extends PApplet {
    int rectX = 225;
    int rectY = 470;
    int rectWidth = 30;
    int rectHeight = 30;
    int speed = 10;

    int redrectX = 0;
    int redrectY = 170;
    int redrectWidth = 60;
    int redrectHeight = 50;

    int greenrectX = 450;
    int greenrectY = 255;
    int greenrectWidth = 60;
    int greenrectHeight = 50;

    // int count = 0; -- for the score count

    boolean moveUp = false, moveDown = false;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {

    }

    public void settings() {
        size(500, 500);

    }

    public void draw() {
        
        background(100, 100, 100);

        stroke(255, 255, 255);
        strokeWeight(8);
        line(0, 150, 500, 150);
        line(0, 325, 500, 325);

        stroke(255, 216, 0);
        strokeWeight(2);
        line(0, 235, 500, 235);
        line(0, 240, 500, 240);

        stroke(60, 179, 113);
        fill(60, 179, 113);
        rect(0, 0, 500, 145);
        rect(0,330,500,170);

        // if(rectY < 145); -- for keeping track of score
        // count ++;

        // fill(0);
        // textSize(25);
        // text("Score:" + count,40,50);

        stroke(0);
        strokeWeight(1);
        fill(210,20,50);
        rect(redrectX,redrectY,redrectWidth,redrectHeight);
        redrectX += speed;

        if(redrectX > 500){
            redrectX = (int)random(-100,0);
        }

        stroke(0);
        strokeWeight(1);
        fill(0,200,0);
        rect(greenrectX,greenrectY,greenrectWidth,greenrectHeight);
        greenrectX -= speed;

        if(greenrectX < 0){
            greenrectX = (int)random(600,500);
        }

        stroke(0);
        strokeWeight(1);
        fill(50, 110, 255);
        rect(rectX, rectY, rectWidth, rectHeight);
        
        

        if (moveUp && rectY > 0)
            rectY -= speed;
        if (moveDown && rectY + rectHeight < height)
            rectY += speed;

    }

    public boolean touching(int redrectX, int redrectY){
        int carLeft = redrectX;
        int carRight = redrectX + greenrectWidth;
        int carTop = redrectY;
        int carBottom = redrectY + greenrectHeight;

        int othercarLeft = greenrectX;
        int othercarRght = greenrectX + greenrectWidth;
        int othercarTop = greenrectY;
        int othercarBottom = greenrectY + greenrectHeight;
    
        if(carLeft < rectX ){
            System.out.println("touching left");
            if(carRight > rectY){
                System.out.println("touching right");
                if(carBottom > rectHeight){
                    System.out.println("touching top");
                    if(carTop > rectWidth){
                        System.out.println("touching bottom");

                    }
                }
            }
            
            
            System.out.println("car hit chicken");
        }
        

    }

    public void keyPressed() {
        if (keyCode == UP)
            moveUp = true;
        if (keyCode == DOWN)
            moveDown = true;
    }

    public void keyReleased() {
        if (keyCode == UP)
            moveUp = false;
        if (keyCode == DOWN)
            moveDown = false;
    }
}
