# ClickableTextWithUrlExample
Example app demonstrating a bug where URLAnnotation is read correctly by Talkback, but the on click is not called.

Steps to Reproduce:
1. Turn on Talkback in Accessibility settings
2. Run app
3. App will display a single line of text
4. Part of this text will be a URLAnnotation containing a link
5. Open the Talkback action menu per the prompt
6. Select "Links" in Talkback menu
7. Select the link to trigger the onClick method call, which opens the browser.

Expected:
* Talkback correctly recognizes the link in the text
* Talkback shows the link in the Talkback menu
* When the link is selected in the Talkback menu, the onClick is called in ClickableText and the user is
navigated to the browser.

Actual:
* The onClick for the ClickableText is never called. Note that it is not the browser navigation that is broken. 
This can be confirmed using the logs.
