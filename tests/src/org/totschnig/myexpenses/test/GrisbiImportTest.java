/*   This file is part of My Expenses.
 *   My Expenses is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   My Expenses is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with My Expenses.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.totschnig.myexpenses.test;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.totschnig.myexpenses.GrisbiImport;
import org.totschnig.myexpenses.Utils.CategoryTree;
import org.totschnig.myexpenses.Utils.Result;

import android.test.InstrumentationTestCase;

import junit.framework.Assert;

public class GrisbiImportTest extends InstrumentationTestCase {
  public void testGrisbi6() {
    InputStream catXML;
    Result result;
    catXML = getInstrumentation().getContext().getResources().openRawResource(R.raw.grisbi);
    result = GrisbiImport.analyzeGrisbiFile(catXML);
    Assert.assertEquals(true, result.success);
    CategoryTree catTree = (CategoryTree) result.extra[0];
    HashMap<Integer,CategoryTree> main = catTree.children();
    Assert.assertEquals(22, main.size());
    Assert.assertEquals(10, main.get(1).children().size());
    ArrayList<String> partiesList = (ArrayList<String>) result.extra[1];
    Assert.assertEquals("Peter Schnock",partiesList.get(0));
  }
  public void testGrisbi5() {
    InputStream catXML;
    Result result;
    catXML = getInstrumentation().getContext().getResources().openRawResource(R.raw.grisbi_050);
    result = GrisbiImport.analyzeGrisbiFile(catXML);
    Assert.assertEquals(true, result.success);
    CategoryTree catTree = (CategoryTree) result.extra[0];
    HashMap<Integer,CategoryTree> main = catTree.children();
    Assert.assertEquals(22, main.size());
    Assert.assertEquals(9, main.get(1).children().size());
    ArrayList<String> partiesList = (ArrayList<String>) result.extra[1];
    Assert.assertEquals("Peter Schnock",partiesList.get(0));
  }

  public void testGrisbiParseError() {
    InputStream catXML;
    Result result;
    catXML = getInstrumentation().getContext().getResources().openRawResource(R.raw.grisbi_error);
    result = GrisbiImport.analyzeGrisbiFile(catXML);
    Assert.assertEquals(false, result.success);
    Assert.assertEquals(org.totschnig.myexpenses.R.string.parse_error_parse_exception, result.message);
  }
}