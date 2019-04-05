Modeled observer design in simple messaging project. 

Unsure whether the User object should have reference to the channels it is subscribed to (seems to be a duplication of
efforts since each channel has reference to its subscribers). At this time, I don't have a good way to test what happens
when a user changes channels.
