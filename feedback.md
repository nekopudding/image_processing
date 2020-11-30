# mp1 Feedback

## Grade: 4.5

| Compilation | Timeout | Duration |
|:-----------:|:-------:|:--------:|
||||

## Test Results
### ca.ubc.ece.cpen221.ip.mp.Level1Tests
| Test Status | Count |
| ----------- | ----- |
|tests|6|
|errors|0|
|skipped|0|
|failures|0|
### ca.ubc.ece.cpen221.ip.mp.Level6Tests
| Test Status | Count |
| ----------- | ----- |
|tests|1|
|errors|0|
|skipped|0|
|failures|0|
### ca.ubc.ece.cpen221.ip.mp.Level2Tests
| Test Status | Count |
| ----------- | ----- |
|tests|3|
|errors|0|
|skipped|0|
|failures|0|
### ca.ubc.ece.cpen221.ip.mp.Level5Tests
| Test Status | Count |
| ----------- | ----- |
|tests|4|
|errors|0|
|skipped|0|
|failures|1|
#### Failed Tests
1. `test_GreenScreen4 (java.lang.AssertionError: expected:<bb24f3333168d69b9ed68e87ed085087a1dd9b3e374f11d7a7fbecd5f6fc1ec7> but was:<4204630d3bf2a8bf09e0fce4f195514045f51819ad6e7ddb8ee6ee6b544cccde>)`
### ca.ubc.ece.cpen221.ip.mp.Level3Tests
| Test Status | Count |
| ----------- | ----- |
|tests|6|
|errors|0|
|skipped|0|
|failures|3|
#### Failed Tests
1. `test_Rotate90 (java.lang.IllegalArgumentException: width must be positive)`
1. `test_Rotate180 (java.lang.IllegalArgumentException: width must be positive)`
1. `test_Rotate360 (java.lang.AssertionError: expected:<a5218591df478670781ac38a95a6047e4227ca40441b0275443c2dc6e494f5f9> but was:<498b50a999615e719f8aab0c6217b4f1976ca8a823947258e9a330ebe7732903>)`

## Comments
Overall good. Task 6 does not work in all cases but it was a good effort.
Good, complete specs. Good use of helper methods. Resolved all issues. Overall clean code.
DFTOutput.java:10:	Found non-transient, non-static member. Please mark as transient or provide accessors.

DFTOutput.java:10:	Private field 'amplitude' could be made final; it is only initialized in the declaration or constructor.

DFTOutput.java:11:	Found non-transient, non-static member. Please mark as transient or provide accessors.

DFTOutput.java:11:	Private field 'phase' could be made final; it is only initialized in the declaration or constructor.

DFTOutput.java:41:	Consider using varargs for methods or constructors which take an array the last parameter.

ImageProcessing.java:11:	All methods are static.  Consider using a utility class instead. Alternatively, you could add a private constructor or make the class abstract to silence this warning.

ImageProcessing.java:37:	Found 'DU'-anomaly for variable 'gsImg1' (lines '37'-'77').

ImageProcessing.java:38:	Found 'DU'-anomaly for variable 'gsImg2' (lines '38'-'77').

ImageProcessing.java:45:	Found 'DD'-anomaly for variable 'vec1' (lines '45'-'55').

ImageProcessing.java:46:	Found 'DD'-anomaly for variable 'vec2' (lines '46'-'56').

ImageProcessing.java:46:	Found 'DU'-anomaly for variable 'vec2' (lines '46'-'77').

ImageProcessing.java:55:	Found 'DD'-anomaly for variable 'vec1' (lines '55'-'55').

ImageProcessing.java:56:	Found 'DD'-anomaly for variable 'vec2' (lines '56'-'56').

ImageProcessing.java:56:	Found 'DU'-anomaly for variable 'vec2' (lines '56'-'77').

ImageProcessing.java:71:	System.out.printf is used

ImageTransformer.java:8:	Avoid unused imports such as 'javax.imageio.ImageIO'

ImageTransformer.java:11:	Avoid unused imports such as 'java.io.File'

ImageTransformer.java:12:	Avoid unused imports such as 'java.sql.SQLOutput'

ImageTransformer.java:27:	The class 'ImageTransformer' has a Modified Cyclomatic Complexity of 5 (Highest = 18).

ImageTransformer.java:27:	The class 'ImageTransformer' has a Standard Cyclomatic Complexity of 5 (Highest = 18).

ImageTransformer.java:27:	The class 'ImageTransformer' has a total cyclomatic complexity of 132 (highest 19).

ImageTransformer.java:27:	This class has too many methods, consider refactoring it.

ImageTransformer.java:29:	Found non-transient, non-static member. Please mark as transient or provide accessors.

ImageTransformer.java:29:	Private field 'image' could be made final; it is only initialized in the declaration or constructor.

ImageTransformer.java:30:	Found non-transient, non-static member. Please mark as transient or provide accessors.

ImageTransformer.java:30:	Private field 'width' could be made final; it is only initialized in the declaration or constructor.

ImageTransformer.java:31:	Found non-transient, non-static member. Please mark as transient or provide accessors.

ImageTransformer.java:31:	Private field 'height' could be made final; it is only initialized in the declaration or constructor.

ImageTransformer.java:143:	Avoid using Literals in Conditional Statements

ImageTransformer.java:145:	Avoid using Literals in Conditional Statements

ImageTransformer.java:151:	Avoid using Literals in Conditional Statements

ImageTransformer.java:153:	Avoid using Literals in Conditional Statements

ImageTransformer.java:159:	Avoid using Literals in Conditional Statements

ImageTransformer.java:161:	Avoid using Literals in Conditional Statements

ImageTransformer.java:210:	Potential violation of Law of Demeter (object not created locally)

ImageTransformer.java:211:	Potential violation of Law of Demeter (object not created locally)

ImageTransformer.java:212:	Potential violation of Law of Demeter (object not created locally)

ImageTransformer.java:242:	Avoid using implementation types like 'HashMap'; use the interface instead

ImageTransformer.java:245:	Found 'DD'-anomaly for variable 'redVals' (lines '245'-'254').

ImageTransformer.java:246:	Found 'DD'-anomaly for variable 'greenVals' (lines '246'-'255').

ImageTransformer.java:247:	Found 'DD'-anomaly for variable 'blueVals' (lines '247'-'256').

ImageTransformer.java:248:	Found 'DU'-anomaly for variable 'counter' (lines '248'-'266').

ImageTransformer.java:254:	Found 'DD'-anomaly for variable 'redVals' (lines '254'-'254').

ImageTransformer.java:254:	Potential violation of Law of Demeter (object not created locally)

ImageTransformer.java:255:	Found 'DD'-anomaly for variable 'greenVals' (lines '255'-'255').

ImageTransformer.java:255:	Potential violation of Law of Demeter (object not created locally)

ImageTransformer.java:256:	Found 'DD'-anomaly for variable 'blueVals' (lines '256'-'256').

ImageTransformer.java:256:	Potential violation of Law of Demeter (object not created locally)

ImageTransformer.java:257:	Found 'DU'-anomaly for variable 'counter' (lines '257'-'266').

ImageTransformer.java:277:	Consider using varargs for methods or constructors which take an array the last parameter.

ImageTransformer.java:306:	The method 'countNeighbors(int, int)' has a cyclomatic complexity of 10.

ImageTransformer.java:310:	Avoid using Literals in Conditional Statements

ImageTransformer.java:315:	Avoid using Literals in Conditional Statements

ImageTransformer.java:317:	Avoid using Literals in Conditional Statements

ImageTransformer.java:318:	Found 'DD'-anomaly for variable 'neighbors' (lines '318'-'320').

ImageTransformer.java:319:	Avoid using Literals in Conditional Statements

ImageTransformer.java:324:	Avoid using Literals in Conditional Statements

ImageTransformer.java:344:	Potential violation of Law of Demeter (object not created locally)

ImageTransformer.java:348:	Potential violation of Law of Demeter (object not created locally)

ImageTransformer.java:352:	Potential violation of Law of Demeter (object not created locally)

ImageTransformer.java:380:	The method 'blockPaint' has a Modified Cyclomatic Complexity of 18.

ImageTransformer.java:380:	The method 'blockPaint' has a Standard Cyclomatic Complexity of 18.

ImageTransformer.java:380:	The method 'blockPaint(int)' has a cyclomatic complexity of 19.

ImageTransformer.java:380:	The method 'blockPaint(int)' has an NPath complexity of 2500, current threshold is 200

ImageTransformer.java:395:	Found 'DD'-anomaly for variable 'remainderRow' (lines '395'-'420').

ImageTransformer.java:395:	Found 'DU'-anomaly for variable 'remainderRow' (lines '395'-'433').

ImageTransformer.java:417:	Found 'DU'-anomaly for variable 'remainderCol' (lines '417'-'433').

ImageTransformer.java:420:	Found 'DU'-anomaly for variable 'remainderRow' (lines '420'-'433').

ImageTransformer.java:455:	Found 'DD'-anomaly for variable 'redVals' (lines '455'-'462').

ImageTransformer.java:456:	Found 'DD'-anomaly for variable 'greenVals' (lines '456'-'463').

ImageTransformer.java:457:	Found 'DD'-anomaly for variable 'blueVals' (lines '457'-'464').

ImageTransformer.java:458:	Found 'DU'-anomaly for variable 'counter' (lines '458'-'473').

ImageTransformer.java:462:	Found 'DD'-anomaly for variable 'redVals' (lines '462'-'462').

ImageTransformer.java:462:	Potential violation of Law of Demeter (object not created locally)

ImageTransformer.java:463:	Found 'DD'-anomaly for variable 'greenVals' (lines '463'-'463').

ImageTransformer.java:463:	Potential violation of Law of Demeter (object not created locally)

ImageTransformer.java:464:	Found 'DD'-anomaly for variable 'blueVals' (lines '464'-'464').

ImageTransformer.java:464:	Potential violation of Law of Demeter (object not created locally)

ImageTransformer.java:465:	Found 'DU'-anomaly for variable 'counter' (lines '465'-'473').

ImageTransformer.java:483:	Consider using varargs for methods or constructors which take an array the last parameter.

ImageTransformer.java:510:	Found 'DU'-anomaly for variable 'original_width' (lines '510'-'540').

ImageTransformer.java:511:	Found 'DU'-anomaly for variable 'original_height' (lines '511'-'540').

ImageTransformer.java:512:	Found 'DU'-anomaly for variable 'original_image' (lines '512'-'540').

ImageTransformer.java:559:	Found 'DD'-anomaly for variable 'amplitude' (lines '559'-'583').

ImageTransformer.java:560:	Found 'DD'-anomaly for variable 'phase' (lines '560'-'585').

ImageTransformer.java:583:	Found 'DD'-anomaly for variable 'amplitude' (lines '583'-'583').

ImageTransformer.java:585:	Found 'DD'-anomaly for variable 'phase' (lines '585'-'585').

ImageTransformer.java:585:	Found 'DD'-anomaly for variable 'phase' (lines '585'-'587').

ImageTransformer.java:587:	Found 'DD'-anomaly for variable 'phase' (lines '587'-'585').

ImageTransformer.java:587:	Found 'DD'-anomaly for variable 'phase' (lines '587'-'587').

ImageTransformer.java:624:	Found 'DD'-anomaly for variable 'backgroundCol' (lines '624'-'627').

ImageTransformer.java:625:	Found 'DD'-anomaly for variable 'backgroundRow' (lines '625'-'632').

ImageTransformer.java:625:	Found 'DD'-anomaly for variable 'backgroundRow' (lines '625'-'641').

ImageTransformer.java:629:	Found 'DD'-anomaly for variable 'backgroundCol' (lines '629'-'627').

ImageTransformer.java:629:	Found 'DU'-anomaly for variable 'backgroundCol' (lines '629'-'646').

ImageTransformer.java:634:	Found 'DD'-anomaly for variable 'backgroundRow' (lines '634'-'632').

ImageTransformer.java:634:	Found 'DD'-anomaly for variable 'backgroundRow' (lines '634'-'641').

ImageTransformer.java:641:	Found 'DD'-anomaly for variable 'backgroundRow' (lines '641'-'632').

ImageTransformer.java:641:	Found 'DD'-anomaly for variable 'backgroundRow' (lines '641'-'641').

ImageTransformer.java:641:	Found 'DU'-anomaly for variable 'backgroundRow' (lines '641'-'646').

ImageTransformer.java:674:	Found 'DU'-anomaly for variable 'maxSize' (lines '674'-'692').

ImageTransformer.java:675:	Found 'DD'-anomaly for variable 'maxCoords' (lines '675'-'686').

ImageTransformer.java:678:	Potential violation of Law of Demeter (method chain calls)

ImageTransformer.java:685:	Found 'DU'-anomaly for variable 'maxSize' (lines '685'-'692').

ImageTransformer.java:686:	Found 'DD'-anomaly for variable 'maxCoords' (lines '686'-'686').

ImageTransformer.java:720:	The method 'pixelScannerRedux(int, int, Color, Boolean, Boolean)' has a cyclomatic complexity of 10.

ImageTransformer.java:721:	Consider using varargs for methods or constructors which take an array the last parameter.

ImageTransformer.java:727:	Found 'DU'-anomaly for variable 'col' (lines '727'-'747').

ImageTransformer.java:728:	Found 'DU'-anomaly for variable 'row' (lines '728'-'747').

ImageTransformer.java:733:	These nested if statements could be combined

ImageTransformer.java:735:	Potential violation of Law of Demeter (object not created locally)

ImageTransformer.java:765:	Consider using varargs for methods or constructors which take an array the last parameter.

ImageTransformer.java:772:	Found 'DD'-anomaly for variable 'count' (lines '772'-'777').

ImageTransformer.java:773:	Found 'DD'-anomaly for variable 'results' (lines '773'-'793').

ImageTransformer.java:776:	Avoid unnecessary comparisons in boolean expressions

ImageTransformer.java:777:	Found 'DD'-anomaly for variable 'count' (lines '777'-'777').

ImageTransformer.java:793:	Found 'DD'-anomaly for variable 'results' (lines '793'-'794').

ImageTransformer.java:794:	Found 'DD'-anomaly for variable 'results' (lines '794'-'795').

ImageTransformer.java:795:	Found 'DD'-anomaly for variable 'results' (lines '795'-'796').

ImageTransformer.java:796:	Found 'DD'-anomaly for variable 'results' (lines '796'-'797').

ImageTransformer.java:810:	Consider using varargs for methods or constructors which take an array the last parameter.

ImageTransformer.java:811:	Found 'DU'-anomaly for variable 'width' (lines '811'-'818').

ImageTransformer.java:827:	Found 'DU'-anomaly for variable 'amp' (lines '827'-'856').

ImageTransformer.java:827:	Potential violation of Law of Demeter (method chain calls)

ImageTransformer.java:828:	Avoid unused local variables such as 'temp'.

ImageTransformer.java:828:	Found 'DU'-anomaly for variable 'temp' (lines '828'-'856').

ImageTransformer.java:828:	Potential violation of Law of Demeter (method chain calls)

ImageTransformer.java:829:	Found 'DU'-anomaly for variable 'dftImage' (lines '829'-'856').

ImageTransformer.java:832:	Found 'DD'-anomaly for variable 'xPos' (lines '832'-'848').

ImageTransformer.java:833:	Found 'DD'-anomaly for variable 'yPos' (lines '833'-'849').

ImageTransformer.java:834:	Found 'DU'-anomaly for variable 'mean' (lines '834'-'856').

ImageTransformer.java:848:	Found 'DD'-anomaly for variable 'xPos' (lines '848'-'848').

ImageTransformer.java:849:	Found 'DD'-anomaly for variable 'yPos' (lines '849'-'849').

LoosePackageCoupling	-	No packages or classes specified

## Test Coverage
### DFTOutput
| Metric | Coverage |
| ------ | -------- |
|BRANCH_MISSED|8|
|LINE_COVERED|6|
|LINE_MISSED|7|
|BRANCH_COVERED|2|
### ImageProcessing
| Metric | Coverage |
| ------ | -------- |
|BRANCH_MISSED|5|
|LINE_COVERED|30|
|LINE_MISSED|3|
|BRANCH_COVERED|9|
### ImageTransformer
| Metric | Coverage |
| ------ | -------- |
|BRANCH_MISSED|26|
|LINE_COVERED|310|
|LINE_MISSED|35|
|BRANCH_COVERED|190|

## Checkstyle Results
### `ImageTransformer.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 8 | 8 | `Unused import - javax.imageio.ImageIO.` |
| 11 | 8 | `Unused import - java.io.File.` |
| 12 | 8 | `Unused import - java.sql.SQLOutput.` |
| 13 | None | `Using the '.*' form of import should be avoided - java.util.*.` |
| 27 | None | `Type Javadoc comment is missing @author tag.` |
| 72 | 47 | `'24' is a magic number.` |
| 72 | 53 | `'0xFF' is a magic number.` |
| 73 | 45 | `'16' is a magic number.` |
| 73 | 51 | `'0xFF' is a magic number.` |
| 74 | 46 | `'24' is a magic number.` |
| 74 | 60 | `'16' is a magic number.` |
| 109 | 47 | `'24' is a magic number.` |
| 109 | 53 | `'0xFF' is a magic number.` |
| 110 | 45 | `'16' is a magic number.` |
| 110 | 51 | `'0xFF' is a magic number.` |
| 111 | 51 | `'0xFF' is a magic number.` |
| 112 | 45 | `'0xFF' is a magic number.` |
| 114 | 35 | `'24' is a magic number.` |
| 114 | 43 | `'255' is a magic number.` |
| 114 | 57 | `'16' is a magic number.` |
| 114 | 65 | `'255' is a magic number.` |
| 114 | 86 | `'255' is a magic number.` |
| 138 | 47 | `'24' is a magic number.` |
| 138 | 53 | `'0xFF' is a magic number.` |
| 139 | 45 | `'16' is a magic number.` |
| 139 | 51 | `'0xFF' is a magic number.` |
| 140 | 51 | `'0xFF' is a magic number.` |
| 141 | 45 | `'0xFF' is a magic number.` |
| 143 | 28 | `'64' is a magic number.` |
| 144 | 27 | `'32' is a magic number.` |
| 145 | 35 | `'128' is a magic number.` |
| 146 | 27 | `'96' is a magic number.` |
| 148 | 27 | `'222' is a magic number.` |
| 151 | 29 | `'64' is a magic number.` |
| 152 | 28 | `'32' is a magic number.` |
| 153 | 36 | `'128' is a magic number.` |
| 154 | 28 | `'96' is a magic number.` |
| 156 | 28 | `'222' is a magic number.` |
| 159 | 30 | `'64' is a magic number.` |
| 160 | 29 | `'32' is a magic number.` |
| 161 | 37 | `'128' is a magic number.` |
| 162 | 29 | `'96' is a magic number.` |
| 164 | 29 | `'222' is a magic number.` |
| 166 | 46 | `'24' is a magic number.` |
| 166 | 60 | `'16' is a magic number.` |
| 182 | 78 | `'||' should be on a new line.` |
| 214 | 53 | `'255' is a magic number.` |
| 214 | 69 | `'255' is a magic number.` |
| 214 | 84 | `'255' is a magic number.` |
| 503 | None | `Line is longer than 100 characters (found 101).` |
| 509 | None | `Comment matches to-do format 'TODO:'.` |
| 510 | 13 | `Name 'original_width' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 511 | 13 | `Name 'original_height' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 512 | 15 | `Name 'original_image' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 515 | 13 | `Name 'new_width' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 515 | 80 | `'180' is a magic number.` |
| 515 | 85 | `'+' should be on a new line.` |
| 516 | 55 | `'180' is a magic number.` |
| 517 | 13 | `Name 'new_height' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 517 | 80 | `'180' is a magic number.` |
| 517 | 85 | `'+' should be on a new line.` |
| 518 | 51 | `'180' is a magic number.` |
| 524 | 21 | `Name 'original_x' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 524 | 94 | `'180' is a magic number.` |
| 524 | 99 | `'+' should be on a new line.` |
| 525 | 75 | `'180' is a magic number.` |
| 525 | 80 | `'+' should be on a new line.` |
| 527 | 21 | `Name 'original_y' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 527 | 95 | `'180' is a magic number.` |
| 527 | 100 | `'+' should be on a new line.` |
| 528 | 75 | `'180' is a magic number.` |
| 528 | 80 | `'+' should be on a new line.` |
| 530 | 56 | `'&&' should be on a new line.` |
| 531 | 49 | `'&&' should be on a new line.` |
| 535 | 54 | `'255' is a magic number.` |
| 535 | 59 | `'255' is a magic number.` |
| 535 | 64 | `'255' is a magic number.` |
| 549 | None | `Comment matches to-do format 'TODO:'.` |
| 551 | 13 | `'width' hides a field.` |
| 552 | 13 | `'height' hides a field.` |
| 570 | 64 | `',' is not followed by whitespace.` |
| 572 | 45 | `'*' is not followed by whitespace.` |
| 572 | 45 | `'*' is not preceded with whitespace.` |
| 573 | 46 | `'*' is not followed by whitespace.` |
| 573 | 46 | `'*' is not preceded with whitespace.` |
| 583 | 61 | `',' is not followed by whitespace.` |
| 583 | 83 | `',' is not followed by whitespace.` |
| 584 | 17 | `'if' is not followed by whitespace.` |
| 584 | 40 | `'1e-7' is a magic number.` |
| 584 | 68 | `'1e-7' is a magic number.` |
| 766 | 13 | `'height' hides a field.` |
| 767 | 13 | `'width' hides a field.` |
| 776 | 34 | `Expression can be simplified.` |
| 811 | 13 | `'width' hides a field.` |
| 812 | 13 | `'height' hides a field.` |
| 847 | 43 | `'0xffffff' is a magic number.` |
| 8 | 8 | `Unused import - javax.imageio.ImageIO.` |
| 11 | 8 | `Unused import - java.io.File.` |
| 12 | 8 | `Unused import - java.sql.SQLOutput.` |
| 13 | None | `Using the '.*' form of import should be avoided - java.util.*.` |
| 27 | None | `Type Javadoc comment is missing @author tag.` |
| 72 | 47 | `'24' is a magic number.` |
| 72 | 53 | `'0xFF' is a magic number.` |
| 73 | 45 | `'16' is a magic number.` |
| 73 | 51 | `'0xFF' is a magic number.` |
| 74 | 46 | `'24' is a magic number.` |
| 74 | 60 | `'16' is a magic number.` |
| 109 | 47 | `'24' is a magic number.` |
| 109 | 53 | `'0xFF' is a magic number.` |
| 110 | 45 | `'16' is a magic number.` |
| 110 | 51 | `'0xFF' is a magic number.` |
| 111 | 51 | `'0xFF' is a magic number.` |
| 112 | 45 | `'0xFF' is a magic number.` |
| 114 | 35 | `'24' is a magic number.` |
| 114 | 43 | `'255' is a magic number.` |
| 114 | 57 | `'16' is a magic number.` |
| 114 | 65 | `'255' is a magic number.` |
| 114 | 86 | `'255' is a magic number.` |
| 138 | 47 | `'24' is a magic number.` |
| 138 | 53 | `'0xFF' is a magic number.` |
| 139 | 45 | `'16' is a magic number.` |
| 139 | 51 | `'0xFF' is a magic number.` |
| 140 | 51 | `'0xFF' is a magic number.` |
| 141 | 45 | `'0xFF' is a magic number.` |
| 143 | 28 | `'64' is a magic number.` |
| 144 | 27 | `'32' is a magic number.` |
| 145 | 35 | `'128' is a magic number.` |
| 146 | 27 | `'96' is a magic number.` |
| 148 | 27 | `'222' is a magic number.` |
| 151 | 29 | `'64' is a magic number.` |
| 152 | 28 | `'32' is a magic number.` |
| 153 | 36 | `'128' is a magic number.` |
| 154 | 28 | `'96' is a magic number.` |
| 156 | 28 | `'222' is a magic number.` |
| 159 | 30 | `'64' is a magic number.` |
| 160 | 29 | `'32' is a magic number.` |
| 161 | 37 | `'128' is a magic number.` |
| 162 | 29 | `'96' is a magic number.` |
| 164 | 29 | `'222' is a magic number.` |
| 166 | 46 | `'24' is a magic number.` |
| 166 | 60 | `'16' is a magic number.` |
| 182 | 78 | `'||' should be on a new line.` |
| 214 | 53 | `'255' is a magic number.` |
| 214 | 69 | `'255' is a magic number.` |
| 214 | 84 | `'255' is a magic number.` |
| 503 | None | `Line is longer than 100 characters (found 101).` |
| 509 | None | `Comment matches to-do format 'TODO:'.` |
| 510 | 13 | `Name 'original_width' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 511 | 13 | `Name 'original_height' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 512 | 15 | `Name 'original_image' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 515 | 13 | `Name 'new_width' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 515 | 80 | `'180' is a magic number.` |
| 515 | 85 | `'+' should be on a new line.` |
| 516 | 55 | `'180' is a magic number.` |
| 517 | 13 | `Name 'new_height' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 517 | 80 | `'180' is a magic number.` |
| 517 | 85 | `'+' should be on a new line.` |
| 518 | 51 | `'180' is a magic number.` |
| 524 | 21 | `Name 'original_x' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 524 | 94 | `'180' is a magic number.` |
| 524 | 99 | `'+' should be on a new line.` |
| 525 | 75 | `'180' is a magic number.` |
| 525 | 80 | `'+' should be on a new line.` |
| 527 | 21 | `Name 'original_y' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 527 | 95 | `'180' is a magic number.` |
| 527 | 100 | `'+' should be on a new line.` |
| 528 | 75 | `'180' is a magic number.` |
| 528 | 80 | `'+' should be on a new line.` |
| 530 | 56 | `'&&' should be on a new line.` |
| 531 | 49 | `'&&' should be on a new line.` |
| 535 | 54 | `'255' is a magic number.` |
| 535 | 59 | `'255' is a magic number.` |
| 535 | 64 | `'255' is a magic number.` |
| 549 | None | `Comment matches to-do format 'TODO:'.` |
| 551 | 13 | `'width' hides a field.` |
| 552 | 13 | `'height' hides a field.` |
| 570 | 64 | `',' is not followed by whitespace.` |
| 572 | 45 | `'*' is not followed by whitespace.` |
| 572 | 45 | `'*' is not preceded with whitespace.` |
| 573 | 46 | `'*' is not followed by whitespace.` |
| 573 | 46 | `'*' is not preceded with whitespace.` |
| 583 | 61 | `',' is not followed by whitespace.` |
| 583 | 83 | `',' is not followed by whitespace.` |
| 584 | 17 | `'if' is not followed by whitespace.` |
| 584 | 40 | `'1e-7' is a magic number.` |
| 584 | 68 | `'1e-7' is a magic number.` |
| 766 | 13 | `'height' hides a field.` |
| 767 | 13 | `'width' hides a field.` |
| 776 | 34 | `Expression can be simplified.` |
| 811 | 13 | `'width' hides a field.` |
| 812 | 13 | `'height' hides a field.` |
| 847 | 43 | `'0xffffff' is a magic number.` |

