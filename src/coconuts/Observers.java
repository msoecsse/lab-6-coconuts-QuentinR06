/*
 * Course:     SWE 2410-111
 * Term:       Fall 2025
 * Assignment: Lab 6 - Coconuts
 * Author:     Quentin Robbins and Evan Hartline
 * Date:       10/23/25
 */
package coconuts;

/**
 * observer interface to attach and watch the subject.
 */
public interface Observers {
    /**
     * method to create the specified update behavior when an interaction occurs.
     * @param subject what is being observed
     */
    void update(ObserverSubject subject);
}
