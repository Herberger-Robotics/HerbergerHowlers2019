/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="BasicDrive", group="Iterative Opmode")
@Disabled
public class basicDrive extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    HardwareClass robot = new HardwareClass();
    boolean f = true;



    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        robot.init(hardwareMap);


        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        Claw();

        if(gamepad1.a)
        {
            f = false;
        }

        if(gamepad1.b)
        {

            f = true;
        }



        if(f == true)
        {
            back2Forward();
        }else if (f == false){
            switchDirection();
        }



        Move(1);


        if(gamepad1.right_bumper)
        {
            rightStrafe();
        }

        if(gamepad1.left_bumper)
        {
            leftStrafe();
        }



    }


    public void Claw()
    {
        if(gamepad2.left_bumper){
            robot.liftClaw.setPower(-0.5);
        }else
        if (gamepad2.right_bumper){
            robot.liftClaw.setPower(0.5);
    }else{
            robot.liftClaw.setPower(0);

        }
        if(gamepad2.a){
            robot.claw.setPower(0.5);

        }else if (gamepad2.b){
            robot.claw.setPower(-0.5);
        }else{
            robot.claw.setPower(0);

        }





    }


    public void Move(double speed){
        double y = 0;
        double x =0;
        x = speed * gamepad1.left_stick_x;
        y = speed * gamepad1.left_stick_y;

        robot.leftFront.setPower(y-x);
        robot.leftBack.setPower(y-x);

        robot.rightFront.setPower(y+x);
        robot.rightBack.setPower(y+x);

    }


    public void switchDirection()
        {
            robot.rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
            robot.rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
            robot.leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
            robot.leftBack.setDirection(DcMotorSimple.Direction.REVERSE);


        }

    public void back2Forward()
    {
        robot.rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        robot. leftBack.setDirection(DcMotorSimple.Direction.FORWARD);

    }


    public void rightStrafe(){

            robot.leftFront.setPower(1);
            robot.leftBack.setPower(-1);

            robot.rightFront.setPower(-1);
            robot.rightBack.setPower(1);
        /*else{
            robot.rightBack.setPower(0);
            robot.rightFront.setPower(0);
            robot.leftFront.setPower(0);
            robot.leftBack.setPower(0);
        }*/





    }
    public void leftStrafe(){

            robot.leftFront.setPower(-1);
            robot.leftBack.setPower(1);

            robot.rightFront.setPower(1);
            robot.rightBack.setPower(-1);
        /*else{
            robot.rightBack.setPower(0); // breaks motors
            robot.rightFront.setPower(0);
            robot.leftFront.setPower(0);
            robot.leftBack.setPower(0);

        }*/


    }



    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        robot.rightBack.setPower(0);
        robot.rightFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.leftFront.setPower(0);
        robot.liftClaw.setPower(0);


    }

}