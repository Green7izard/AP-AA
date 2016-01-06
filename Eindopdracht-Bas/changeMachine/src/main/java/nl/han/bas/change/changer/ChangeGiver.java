package nl.han.bas.change.changer;

/**
 * Created by Bas on 6-1-2016.
 */
public interface ChangeGiver
{
    /**
     * Gets the change for a specified amount
     * Unfound money will be added under -1
     *
     * @param amount the amount
     * @return a Change object
     */
    Change getChange(int amount);
}
