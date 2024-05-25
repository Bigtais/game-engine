package Utils;

/**
 * Representation of a 2D vector.
 */
public class Vector2 {

    /**
     * The x attribute of the vector.
     */
    public float x;

    /**
     * The y attribute of the vector.
     */
    public float y;

    /**
     * Default constructor for Vector2
     */
    public Vector2()
    {
        x=0;
        y=0;
    }

    /**
     * Creates a 2d vector with the provided x and y coordinates
     * @param x The x attribute of the vector.
     * @param y The y attribute of the vector.
     */
    public Vector2(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a 2d vector from the provided vector.
     * @param v The vector to copy.
     */
    public Vector2(Vector2 v)
    {
        x = v.x;
        y = v.y;
    }

    /**
     * Additions the provided vector to the current vector
     * and modifies the current vector to the new value.
     * @param operand The vector to addition.
     * @return The current modified vector
     */
    public Vector2 add(Vector2 operand){
        return new Vector2(x+operand.x, y+ operand.y);
    }

    /**
     * Subtracts the provided vector from the current vector
     * and modifies the current vector to the new value.
     * @param operand The vector to subtract.
     * @return The new vector
     */
    public Vector2 sub(Vector2 operand){
        return new Vector2(x-operand.x, y- operand.y);
    }

    /**
     * Multiplies the provided vector to the current vector
     * and modifies the current vector to the new value.
     * @param operand The vector to multiply.
     * @return The new vector
     */
    public Vector2 mult(Vector2 operand){
        return new Vector2(x* operand.x, y* operand.y);
    }

    /**
     * Divides the provided vector from the current vector
     * and modifies the current vector to the new value.
     * @param operand The vector that divides.
     * @return The new vector
     */
    public Vector2 divide(Vector2 operand){
        return new Vector2(x/ operand.x, y/ operand.y);
    }

    /**
     * Returns the absolute value of the current vector.
     * @return The absolute value of the current vector.
     */
    public Vector2 abs()
    {
        return new Vector2(Math.abs(x), Math.abs(y));
    }

    /**
     * Normalize the vector by dividing it's value by it's length
     * @return a new vector normalized
     */
    public Vector2 normalize()
    {
        float longueur = (float) Math.sqrt(x*x+y*y);
        return new Vector2(x/longueur, y/longueur);
    }

    /**
     * calculate the rotation of this vector
     * @return the rotation
     */
    public float getRotation(){
        if(x==0)
        {
            return y > 0 ? 90 : 270;
        }
        if(y==0)
        {
            return x > 0 ? 0 : 180;
        }
        float angle = (float) Math.atan(y/x);
        return (float) (angle * 180/Math.PI);
    }

    public boolean equals(Vector2 o) {
        return x == o.x && y == o.y;
    }

    public float distance(Vector2 o) {
        return (float) Math.sqrt(Math.pow((x - o.x), 2) + Math.pow((y - o.y), 2));
    }

    @Override
    public String toString() {
        return "Vector2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
