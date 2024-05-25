package Utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2Test {

    Vector2 vectorOne, vectorMinusOne;

    @BeforeEach
    void init(){
        vectorOne = new Vector2(1, 1);
        vectorMinusOne = new Vector2(-1, 1);
    }

    @Test
    void add() {
        Vector2 v = new Vector2(2,3);

        // Add (1,1) with (2,3)
        Vector2 returned = vectorOne.add(v);

        assertEquals(1 + 2, returned.x);

        assertEquals(1 + 3, returned.y);


        // Add (-1,1) with (2,3)
        returned = vectorMinusOne.add(v);

        assertEquals(-1 + 2, returned.x);

        assertEquals(1 + 3, returned.y);

    }

    @Test
    void sub() {
        Vector2 v = new Vector2(2,3);

        // sub (2,3) from (1,1)
        Vector2 returned = vectorOne.sub(v);

        assertEquals(1 - 2, returned.x);

        assertEquals(1 - 3, returned.y);

        // sub (2,3) from (-1,1)
        returned = vectorMinusOne.sub(v);

        assertEquals(-1 - 2, returned.x);

        assertEquals(1 - 3, returned.y);
    }

    @Test
    void mult() {
        Vector2 v = new Vector2(2,3);

        // Multiply (1,1) with (2,3)
        Vector2 returned = vectorOne.mult(v);

        assertEquals(2, returned.x);

        assertEquals(3, returned.y);


        // Multiply (-1,1) with (2,3)
        returned = vectorMinusOne.mult(v);

        assertEquals(-2, returned.x);

        assertEquals(3, returned.y);
    }

    @Test
    void divide() {
        Vector2 v = new Vector2(2,3);

        // Divide (1,1) with (2,3)
        Vector2 returned = vectorOne.divide(v);

        assertEquals((float) 1 / 2, returned.x);

        assertEquals((float) 1 / 3, returned.y);


        // Divide (-1,1) with (2,3)
        returned = vectorMinusOne.divide(v);

        assertEquals((float) -1 / 2, returned.x);

        assertEquals((float) 1 / 3, returned.y);
    }

    @Test
    void abs() {
        // Absolute value of (1,1) doesn't change
        Vector2 returned = vectorOne.abs();

        assertEquals(1, returned.x);
        assertEquals(1, returned.y);

        // Absolute value of (-1,1) is (1,1)
        returned = vectorMinusOne.abs();

        assertEquals(1, returned.x);
        assertEquals(1, returned.y);
    }

    @Test
    void normalize()
    {
        //v has a length of 5
        Vector2 v = new Vector2(3,4);
        Vector2 returned = v.normalize();

        assertEquals(v.x/5, returned.x);
        assertEquals(v.y/5, returned.y);
    }

    @Test
    void getRotation(){
        Vector2 rightAngle = new Vector2(1, 0);
        Vector2 upAngle = new Vector2(0, 1);
        Vector2 downAngle = new Vector2(0, -1);
        Vector2 leftAngle = new Vector2(-1, 0);
        Vector2 upRightAngle = new Vector2(1, 1);

        assertEquals(0, rightAngle.getRotation());
        assertEquals(180.0, leftAngle.getRotation());
        assertEquals(90, upAngle.getRotation());
        assertEquals(270, downAngle.getRotation());
        assertEquals(45, upRightAngle.getRotation());
    }
}