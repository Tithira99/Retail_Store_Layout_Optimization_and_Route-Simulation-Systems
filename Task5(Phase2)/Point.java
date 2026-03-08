public class Point {
    int x;
    int y;
    int entX;
    int entY;
    String name;
    String direction;
    
    Point(){
    }

    Point(int xIn, int yIn){
        this.entX=xIn;
        this.entY=yIn;
    }
    
    Point(int xIn, int yIn, String name, String dir){
        this.x=xIn;
        this.y=yIn;
        this.name=name;
        this.direction=dir;

        if(dir.equals("N")){
            this.entX=xIn;
            this.entY=yIn+1;
        }else if(dir.equals("E")){
            this.entX=xIn+1;
            this.entY=yIn;
        }else if(dir.equals("S")){
            this.entX=xIn;
            this.entY=yIn-1;
        }else{
            this.entX=xIn-1;
            this.entY=yIn;
        }
    }
    
    
    int getX(){
        return this.x;
    }

    int getY(){
        return this.y;
    }

    String getName(){
        return this.name;
    }

    String getDir(){
        return this.direction;
    }

    int getentX(){
        return this.entX;
    }

    int getentY(){
        return this.entY;
    }

    
}
