# PracticeRobot-2019
Patrick's 2019 Offseason Repository

For 2019 our practice robot will convert into Patrick, the legendary cargo robot of the offseason.
He will have a rotating arm somewhat like millenium falcons allowing us to pick up cargo 
from the ground and score on the cargo ship. The claw will not score on either side, only 
in the front. He will also climb using our 14 inch cylinders. 
He will possibly vision align with the pixycam and may have a usb camera for driver vision.  

*Drivebase:*
Style: Standard Andymark Drivebase  
Motors: 4 CIMs, 2 on each side all controlled by Victor SPXs.  

*Rotating Arm:*
Motors: 2 CIMs controlled by Talon SRXs  
Limits: 2 limit switches at both mechanical limits  
Encoder: [An Andymark Absolute Encoder](https://www.andymark.com/products/absolute-encoder-with-cable)  

*Climber*  
Pneumatics: 2 solenoids (or double solenoids, code in comments)  
Motors: A bag motor controlled by a spark.  
Limits: 1 limit switch on each leg.  

*Vision*  
Alignment: PixyCam (original), [see Sandy](https://github.com/frc6995/Robot-2019/tree/sammcdo/PixyCam)  
Drivers: 1 USB Camera  
