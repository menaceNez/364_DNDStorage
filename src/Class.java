public class Class {

    // https://www.youtube.com/watch?v=Bi48mFHWmX8
    public int classID; 
    public String className; 

    public  Class(String name){
        this.className = name; 
    }

    public String getClassName(){
        return className; 
    }

    public void setClassName(String name){
        this.className = name; 
    }

    public void setClassID(int ID){
        this.classID = ID; 
    }

    public int getClassID(){
        return classID; 
    }


}
