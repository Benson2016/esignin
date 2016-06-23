<%--
  Created by IntelliJ IDEA.
  User: benson
  Date: 16/5/31
  Time: 00:24
  To change this template use File | Settings | File Templates.
--%>
    <!-- BEGIN DASHBOARD STATS -->
    <div class="row-fluid">
        <div class="span3 responsive" data-tablet="span6" data-desktop="span3">
            <div class="dashboard-stat blue">
                <div class="visual">
                    <i class="icon-comments"></i>
                </div>
                <div class="details">
                    <div class="number">
                        ${newRecordCount}
                    </div>
                    <div class="desc">
                        New Notifications
                    </div>
                </div>
                <a class="more" href="#">
                    View more <i class="m-icon-swapright m-icon-white"></i>
                </a>
            </div>
        </div>

        <div class="span3 responsive" data-tablet="span6" data-desktop="span3">
            <div class="dashboard-stat green">
                <div class="visual">
                    <i class="icon-user"></i>
                </div>
                <div class="details">
                    <div class="number">${newUserCount}</div>
                    <div class="desc">New Users</div>
                </div>
                <a class="more" href="#">
                    View more <i class="m-icon-swapright m-icon-white"></i>
                </a>
            </div>
        </div>

        <div class="span3 responsive" data-tablet="span6  fix-offset" data-desktop="span3">
            <div class="dashboard-stat purple">
                <div class="visual">
                    <i class="icon-globe"></i>
                </div>
                <div class="details">
                    <div class="number">+30%</div>
                    <div class="desc">ESignIn Popularity</div>
                </div>
                <a class="more" href="#">
                    View more <i class="m-icon-swapright m-icon-white"></i>
                </a>
            </div>
        </div>

        <div class="span3 responsive" data-tablet="span6" data-desktop="span3">
            <div class="dashboard-stat yellow">
                <div class="visual">
                    <i class="icon-bell"></i>
                </div>
                <div class="details">
                    <div class="number">0</div>
                    <div class="desc">System Exception</div>
                </div>
                <a class="more" href="#">
                    View more <i class="m-icon-swapright m-icon-white"></i>
                </a>
            </div>
        </div>

    </div>
    <!-- END DASHBOARD STATS -->
    <div class="clearfix"></div>
