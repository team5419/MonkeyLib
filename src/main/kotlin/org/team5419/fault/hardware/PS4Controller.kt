package org.team5419.fault.hardware

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.GenericHID.Hand

/**
 * PlayStation 4 controller abstraction.
 * @param portNumber What port the controller is on.
 */
@SuppressWarnings("TooManyFunctions", "VariableNaming")
public class PS4Controller(portNumber: Int) : Joystick(portNumber) {

    // All ports on the PS4 Controller.
    private val SQUARE_BUTTON_PORT: Int = 1
    private val X_BUTTON_PORT: Int = 2
    private val O_BUTTON_PORT: Int = 3
    private val TRIANGLE_BUTTON_PORT: Int = 4
    private val LEFT_BUMPER_PORT: Int = 5
    private val RIGHT_BUMPER_PORT: Int = 6
    private val LEFT_TRIGGER_PORT: Int = 7
    private val RIGHT_TRIGGER_PORT: Int = 8
    private val SHARE_BUTTON_PORT: Int = 9
    private val OPTIONS_BUTTON_PORT: Int = 10
    private val LEFT_STICK_BUTTON_PORT: Int = 11
    private val RIGHT_STICK_BUTTON_PORT: Int = 12
    private val HOME_BUTTON_PORT: Int = 13
    private val TOUCHPAD_BUTTON_PORT: Int = 14

    /**
     * Gets if the square button has been pressed.
     * @return If the square button has been pressed.
     */
    public fun getSquareButtonPressed(): Boolean { return getRawButtonPressed(SQUARE_BUTTON_PORT) }

    /**
     * Gets if the square button has been released.
     * @return If the square button has been released.
     */
    public fun getSquareButtonReleased(): Boolean { return getRawButtonReleased(SQUARE_BUTTON_PORT) }

    /**
     * Gets the current value of the square button.
     * @return The current value of the square button.
     */
    public fun getSquareButton(): Boolean { return getRawButton(SQUARE_BUTTON_PORT) }

    /**
     * Gets if the X button has been pressed.
     * @return If the X button has been pressed.
     */
    public fun getXButtonPressed(): Boolean { return getRawButtonPressed(X_BUTTON_PORT) }

    /**
     * Gets if the X button has been released.
     * @return If the X button has been released.
     */
    public fun getXButtonReleased(): Boolean { return getRawButtonReleased(X_BUTTON_PORT) }

    /**
     * Gets the current value of the X button.
     * @return The current value of the X button.
     */
    public fun getXButton(): Boolean { return getRawButton(X_BUTTON_PORT) }

    /**
     * Gets if the O button has been pressed.
     * @return If the O button has been pressed.
     */
    public fun getOButtonPressed(): Boolean { return getRawButtonPressed(O_BUTTON_PORT) }

    /**
     * Gets if the O button has been released.
     * @return If the O button has been released.
     */
    public fun getOButtonReleased(): Boolean { return getRawButtonReleased(O_BUTTON_PORT) }

    /**
     * Gets the current value of the O button.
     * @return The current value of the O button.
     */
    public fun getOButton(): Boolean { return getRawButton(O_BUTTON_PORT) }

    /**
     * Gets if the triangle button has been pressed.
     * @return If the triangle button has been pressed.
     */
    public fun getTriangleButtonPressed(): Boolean { return getRawButtonPressed(TRIANGLE_BUTTON_PORT) }

    /**
     * Gets if the triangle button has been released.
     * @return If the triangle button has been released.
     */
    public fun getTriangleButtonReleased(): Boolean { return getRawButtonReleased(TRIANGLE_BUTTON_PORT) }

    /**
     * Gets the current value of the triangle button.
     * @return The current value of the triangle button.
     */
    public fun getTriangleButton(): Boolean { return getRawButton(TRIANGLE_BUTTON_PORT) }

    /**
     * Gets if a selected bumper has been pressed.
     * @param hand Which side of the controller.
     * @return If a selected bumper has been pressed.
     */
    public fun getBumperPressed(hand: Hand): Boolean {
        when (hand) {
            Hand.kLeft -> return getRawButtonPressed(LEFT_BUMPER_PORT)
            Hand.kRight -> return getRawButtonPressed(RIGHT_BUMPER_PORT)
        }
    }

    /**
     * Gets if a selected bumper has been released.
     * @param hand Which side of the controller.
     * @return If a selected bumper has been released.
     */
    public fun getBumperReleased(hand: Hand): Boolean {
        when (hand) {
            Hand.kLeft -> return getRawButtonReleased(LEFT_BUMPER_PORT)
            Hand.kRight -> return getRawButtonReleased(RIGHT_BUMPER_PORT)
        }
    }

    /**
     * Gets the value of a selected bumper.
     * @param hand Which side of the controller.
     * @return The current value of a selected bumper.
     */
    public fun getBumper(hand: Hand): Boolean {
        when (hand) {
            Hand.kLeft -> return getRawButton(LEFT_BUMPER_PORT)
            Hand.kRight -> return getRawButton(RIGHT_BUMPER_PORT)
        }
    }

    /**
     * Gets if a selected trigger has been pressed.
     * @param hand Which side of the controller.
     * @return If a selected trigger has been pressed.
     */
    public fun getTriggerPressed(hand: Hand): Boolean {
        when (hand) {
            Hand.kLeft -> return getRawButtonPressed(LEFT_TRIGGER_PORT)
            Hand.kRight -> return getRawButtonPressed(RIGHT_TRIGGER_PORT)
        }
    }

    /**
     * Gets if a selected trigger has been released.
     * @param hand Which side of the controller.
     * @return If a selected trigger has been released.
     */
    public fun getTriggerReleased(hand: Hand): Boolean {
        when (hand) {
            Hand.kLeft -> return getRawButtonReleased(LEFT_TRIGGER_PORT)
            Hand.kRight -> return getRawButtonReleased(RIGHT_TRIGGER_PORT)
        }
    }

    /**
     * Gets the value of a selected trigger.
     * @param hand Which side of the controller.
     * @return The current value of a selected trigger.
     */
    public fun getTrigger(hand: Hand): Boolean {
        when (hand) {
            Hand.kLeft -> return getRawButton(LEFT_TRIGGER_PORT)
            Hand.kRight -> return getRawButton(RIGHT_TRIGGER_PORT)
        }
    }

    /**
     * Gets if the share button has been pressed.
     * @return If the share button has been pressed.
     */
    public fun getShareButtonPressed(): Boolean { return getRawButtonPressed(SHARE_BUTTON_PORT) }

    /**
     * Gets if the share button has been released.
     * @return If the share button has been released.
     */
    public fun getShareButtonReleased(): Boolean { return getRawButtonReleased(SHARE_BUTTON_PORT) }

    /**
     * Gets the current value of the share button.
     * @return The current value of the share button.
     */
    public fun getShareButton(): Boolean { return getRawButton(SHARE_BUTTON_PORT) }

    /**
     * Gets if the options button has been pressed.
     * @return If the options button has been pressed.
     */
    public fun getOptionsButtonPressed(): Boolean { return getRawButtonPressed(OPTIONS_BUTTON_PORT) }

    /**
     * Gets if the options button has been released.
     * @return If the options button has been released.
     */
    public fun getOptionsButtonReleased(): Boolean { return getRawButtonReleased(OPTIONS_BUTTON_PORT) }

    /**
     * Gets the current value of the options button.
     * @return The current value of the options button.
     */
    public fun getOptionsButton(): Boolean { return getRawButton(OPTIONS_BUTTON_PORT) }

    /**
     * Gets if the selected stick has been pressed.
     * @param hand Which side of the controller.
     * @return If the selected stick has been pressed.
     */
    public fun getStickButtonPressed(hand: Hand): Boolean {
        when (hand) {
            Hand.kLeft -> return getRawButtonPressed(LEFT_STICK_BUTTON_PORT)
            Hand.kRight -> return getRawButtonPressed(RIGHT_STICK_BUTTON_PORT)
        }
    }

    /**
     * Gets if the selected stick has been released.
     * @param hand Which side of the controller.
     * @return If the selected stick has been released.
     */
    public fun getStickButtonReleased(hand: Hand): Boolean {
        when (hand) {
            Hand.kLeft -> return getRawButtonReleased(LEFT_STICK_BUTTON_PORT)
            Hand.kRight -> return getRawButtonReleased(RIGHT_STICK_BUTTON_PORT)
        }
    }

    /**
     * Gets the current value of the selected stick.
     * @param hand Which side of the controller.
     * @return The current value of the selected stick.
     */
    public fun getStickButton(hand: Hand): Boolean {
        when (hand) {
            Hand.kLeft -> return getRawButton(LEFT_STICK_BUTTON_PORT)
            Hand.kRight -> return getRawButton(RIGHT_STICK_BUTTON_PORT)
        }
    }

    /**
     * Gets if the home button has been pressed.
     * @return If the home button has been pressed.
     */
    public fun getHomeButtonPressed(): Boolean { return getRawButtonPressed(HOME_BUTTON_PORT) }

    /**
     * Gets if the home button has been released.
     * @return If the home button has been released.
     */
    public fun getHomeButtonReleased(): Boolean { return getRawButtonReleased(HOME_BUTTON_PORT) }

    /**
     * Gets the current value of the home button.
     * @return The current value of the home button.
     */
    public fun getHomeButton(): Boolean { return getRawButton(HOME_BUTTON_PORT) }

    /**
     * Gets if the touch pad has been pressed.
     * @return If the touch pad has been pressed.
     */
    public fun getTouchPadPressed(): Boolean { return getRawButtonPressed(TOUCHPAD_BUTTON_PORT) }

    /**
     * Gets if the touch pad has been released.
     * @return If the touch pad has been released.
     */
    public fun getTouchPadReleased(): Boolean { return getRawButtonReleased(TOUCHPAD_BUTTON_PORT) }

    /**
     * Gets the current value of the touch pad.
     * @return The current value of the touch pad.
     */
    public fun getTouchPad(): Boolean { return getRawButton(TOUCHPAD_BUTTON_PORT) }

    /**
     * Gets if the D-Pad is in the up position.
     * @param error The error in the range of the angle.
     * @return If the D-Pad is within the allowed range.
     */
    public fun getPOVUp(error: Int): Boolean { return getPOV() <= 1 + error && getPOV() >= 360 - error }

    /**
     * Gets if the D-Pad is in the left position.
     * @param error The error in the range of the angle.
     * @return If the D-Pad is within the allowed range.
     */
    public fun getPOVLeft(error: Int): Boolean { return getPOV() <= 270 + error && getPOV() >= 270 - error }

    /**
     * Gets if the D-Pad is in the down position.
     * @param error The error in the range of the angle.
     * @return If the D-Pad is within the allowed range.
     */
    public fun getPOVDown(error: Int): Boolean { return getPOV() <= 180 + error && getPOV() >= 180 - error }

    /**
     * Gets if the D-Pad is in the right position.
     * @param error The error in the range of the angle.
     * @return If the D-Pad is within the allowed range.
     */
    public fun getPOVRight(error: Int): Boolean { return getPOV() <= 90 + error && getPOV() >= 90 - error }
}
