/*
 * Course:     SWE 2410-111
 * Term:       Fall 2025
 * Assignment: Lab 6 - Coconuts
 * Author:     Quentin Robbins and Evan Hartline
 * Date:       10/23/25
 */
package coconuts;

/**
 * subject interface to allow observes to attach, detach, and be updated by the subject.
 */
public interface ObserverSubject {
    /**
     * attach an observer to the subject
     * @param o observer
     */
    void attach(Observers o);

    /**
     * detach an observer from the subject
     * @param o observer
     */
    void detach(Observers o);

    /**
     * update all observers attached to the subject
     */
    void notifyAllObservers();
}


